package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.ChangePasswordDTO;
import pl.edu.prz.master.thesis.backend.dto.UserDTO;
import pl.edu.prz.master.thesis.backend.entity.User;
import pl.edu.prz.master.thesis.backend.repository.StudentRepository;
import pl.edu.prz.master.thesis.backend.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<UserDTO> getUsers(String email) {
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateOrAddUser(User user, Long id) {

    }

    @Override
    public void deactivateUser(Long id) {

    }

    @Override
    public void deleteUserFromDatabase(Long id) {

    }

    @Override
    public String encodePassword(String password) {
        return null;
    }

    @Override
    public boolean comparePasswords(String rawPassword, String encodedPassword) {
        return false;
    }

    @Override
    public boolean validPassword(String password) {
        return false;
    }

    @Override
    public void changePassword(HttpServletRequest request, ChangePasswordDTO changePasswordDTO) {

    }

    @Override
    public String generatePassword() {
        return null;
    }

    @Override
    public User fillEntity(User user) {
        return null;
    }
}
