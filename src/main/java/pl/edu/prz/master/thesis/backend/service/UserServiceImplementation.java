package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.ChangePasswordDTO;
import pl.edu.prz.master.thesis.backend.dto.UserDTO;
import pl.edu.prz.master.thesis.backend.entity.User;
import pl.edu.prz.master.thesis.backend.enums.Status;
import pl.edu.prz.master.thesis.backend.repository.UserRepository;
import pl.edu.prz.master.thesis.backend.security.TokenComponent;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final TokenComponent tokenComponent;
    private final ModelMapper modelMapper;

    @Override
    public List<UserDTO> getUsers(String email) {
        if (email != null && !email.equals("")) {
            return Collections.singletonList(getUserByEmail(email));
        }

        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Unable to find user with id" + id));
            return modelMapper.map(user, UserDTO.class);
        } else {
            throw new EntityNotFoundException("User Not Found");
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Unable to find user with email " + email));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
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
    }

    @Override
    public void updateOrAddUser(User user, Long id) {
        user.setId(id);
        if (userRepository.existsById(id)) {
            user.setPassword(userRepository.getOne(id).getPassword());
        } else {
            String password = generatePassword();
            user.setPassword(encodePassword(password));
        }
        userRepository.save(fillEntity(user));
    }

    @Override
    public void deactivateUser(Long id) {
        User user = userRepository.getOne(id);
        user.setStatus(Status.INACTIVE);
        userRepository.save(user);
    }

    @Override
    public void deleteUserFromDatabase(Long id) {
        User user = userRepository.getOne(id);
        userRepository.deleteById(user.getId());
    }

    @Override
    public String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public boolean comparePasswords(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    @Override
    public boolean validPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$");
    }

    @Override
    public void changePassword(HttpServletRequest request, ChangePasswordDTO changePasswordDTO) {
        UserDTO userDTO = getUserById(tokenComponent.getIdFromToken(tokenComponent.getToken(request)));
        if (!comparePasswords(changePasswordDTO.getOldPassword(), userDTO.getPassword())) {
            throw new AccessDeniedException("Passwords do not match.");
        }
        if (!validPassword(changePasswordDTO.getNewPassword())) {
            throw new AccessDeniedException("New password is too weak.");
        }
        userDTO.setPassword(encodePassword(changePasswordDTO.getNewPassword()));
        User user = modelMapper.map(userDTO, User.class);
        user.setId(userDTO.getId());
        user.setLastPasswordModified(new Date());
        userRepository.save(user);
    }

    @Override
    public String generatePassword() {
        return RandomStringUtils.random(10, true, true);
    }

    @Override
    public User fillEntity(User user) {
        return user;
    }
}
