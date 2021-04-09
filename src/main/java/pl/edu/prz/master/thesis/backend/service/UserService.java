package pl.edu.prz.master.thesis.backend.service;

import pl.edu.prz.master.thesis.backend.dto.ChangePasswordDTO;
import pl.edu.prz.master.thesis.backend.dto.UserDTO;
import pl.edu.prz.master.thesis.backend.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    List<UserDTO> getUsers(String email);

    UserDTO getUserById(Long id);

    UserDTO getUserByEmail(String email);

    void createUser(User user);

    void updateOrAddUser(User user, Long id);

    void deactivateUser(Long id);

    void deleteUserFromDatabase(Long id);

    String encodePassword(String password);

    boolean comparePasswords(String rawPassword, String encodedPassword);

    boolean validPassword(String password);

    void changePassword(HttpServletRequest request, ChangePasswordDTO changePasswordDTO);

    String generatePassword();

    User fillEntity(User user);

}
