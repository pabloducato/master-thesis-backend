package pl.edu.prz.master.thesis.backend.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.ChangePasswordDTO;
import pl.edu.prz.master.thesis.backend.dto.UserDTO;
import pl.edu.prz.master.thesis.backend.entity.ResetPasswordEntity;
import pl.edu.prz.master.thesis.backend.entity.Status;
import pl.edu.prz.master.thesis.backend.entity.User;
import pl.edu.prz.master.thesis.backend.repository.PasswordResetTokenRepository;
import pl.edu.prz.master.thesis.backend.repository.StudentRepository;
import pl.edu.prz.master.thesis.backend.repository.UserRepository;
import pl.edu.prz.master.thesis.backend.security.TokenHelper;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final TokenHelper tokenHelper;
    private final EmailComponent emailComponent;
    private final StudentRepository studentRepository;


    public UserService(TokenHelper tokenHelper, UserRepository userRepository, EmailComponent emailComponent,
                       PasswordResetTokenRepository passwordResetTokenRepository, StudentRepository studentRepository) {
        this.tokenHelper = tokenHelper;
        this.userRepository = userRepository;
        this.emailComponent = emailComponent;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.studentRepository = studentRepository;
    }


    public List<UserDTO> getUsers(String email) {
        if (email != null && !email.equals("")) {
            return Collections.singletonList(getUserByEmail(email));
        }

        return userRepository.findAll()
                .stream()
                .map(User::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).get().mapToDTO();
    }

    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Unable to find user with email " + email)).mapToDTO();
    }

    public void createUser(User user) {
        String password;
        if (user.getPassword() == null) {
            password = generatePassword();
        } else if (validPassword(user.getPassword())) {
            password = user.getPassword();
        } else {
            throw new AccessDeniedException("Password is too weak.");
        }

        user.setPassword(encodePassword(password));
        userRepository.save(fillEntity(user));
//        String text = String.format(emailSender.templateNewUserMessage().getText(), password);
//        emailSender.sendSimpleMessage(user.getEmail(), "New password", text);
    }

    public void updateOrAddUser(User user, Long id) {
        user.setId(id);
        if (userRepository.existsById(id)) {
            user.setPassword(userRepository.getOne(id).getPassword());
        } else {
            String password = generatePassword();
            user.setPassword(encodePassword(password));
//      String text = String.format(emailSender.templateNewUserMessage().getText(), password);
//      emailSender.sendSimpleMessage(user.getEmail(), "New password", text);
        }
        userRepository.save(fillEntity(user));
    }

    public void deactivateUser(Long id) {
        User user = userRepository.getOne(id);
        user.setStatus(Status.INACTIVE);
        userRepository.save(user);
    }

    public void deleteUserFromDatabase(Long id) {
        User user = userRepository.getOne(id);
        userRepository.deleteById(user.getId());
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private boolean comparePasswords(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    private boolean validPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$");
    }

    public void changePassword(HttpServletRequest request, ChangePasswordDTO changePasswordDTO) {
        UserDTO userDTO = getUserById(tokenHelper.getIdFromToken(tokenHelper.getToken(request)));
        if (!comparePasswords(changePasswordDTO.getOldPassword(), userDTO.getPassword())) {
            throw new AccessDeniedException("Passwords do not match.");
        }
        if (!validPassword(changePasswordDTO.getNewPassword())) {
            throw new AccessDeniedException("New password is too weak.");
        }
        userDTO.setPassword(encodePassword(changePasswordDTO.getNewPassword()));
        User user = userDTO.parseUser();
        user.setId(userDTO.getId());
        user.setLastPasswordModified(new Date());
        userRepository.save(user);
    }

    public void generatePasswordResetToken(String email) {
        UserDTO userDTO = getUserByEmail(email);

        ResetPasswordEntity passwordResetToken;
        if (passwordResetTokenRepository.existsByUserId(userDTO.getId())) {
            passwordResetToken = passwordResetTokenRepository.findByUserId(userDTO.getId());
            if (passwordResetToken.isValid()) {
                throw new AccessDeniedException("Mail has already sent.");
            }
            passwordResetToken.setExpiryDate(ResetPasswordEntity.calculateExpiryDate());
        } else {
            passwordResetToken = new ResetPasswordEntity(userDTO.getId());
        }
        String token = UUID.randomUUID().toString();
        passwordResetToken.setToken(token);
        String link = emailComponent.generateLinkToReset(userDTO.getId(), token);
        String text = String.format(Objects.requireNonNull(emailComponent.templateResetTokenMessage().getText()), link);
        emailComponent.sendSimpleMessage(email, "Reset your password", text);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    public void validatePasswordResetToken(long id, String token) {
        ResetPasswordEntity passToken =
                passwordResetTokenRepository.findByToken(token);
        if ((passToken == null) || (passToken.getUser().getId() != id)) {
            throw new AccessDeniedException("Invalid Token or User");
        }
        if (!passToken.isValid()) {
            throw new AccessDeniedException("Token expired");
        }
        User user = passToken.getUser();
        String password = generatePassword();
        user.setPassword(encodePassword(password));
        user.setLastPasswordModified(new Date());
        userRepository.save(user);
        passwordResetTokenRepository.delete(passToken);
        String text = String.format(Objects.requireNonNull(emailComponent.templateNewPasswordMessage().getText()), password);
        emailComponent.sendSimpleMessage(user.getEmail(), "New password", text);
    }

    private String generatePassword() {
        return RandomStringUtils.random(10, true, true);
    }


    private User fillEntity(User user) {
        return user;
    }


}
