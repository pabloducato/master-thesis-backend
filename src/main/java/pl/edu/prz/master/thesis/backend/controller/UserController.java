package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.ChangePasswordDTO;
import pl.edu.prz.master.thesis.backend.dto.UserDTO;
import pl.edu.prz.master.thesis.backend.entity.UserTokenEntity;
import pl.edu.prz.master.thesis.backend.security.TokenHelper;
import pl.edu.prz.master.thesis.backend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = "User Controller")
@RequestMapping(value = "/api/users", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final TokenHelper tokenHelper;

    public UserController(UserService userService, TokenHelper tokenHelper) {
        this.userService = userService;
        this.tokenHelper = tokenHelper;
    }

    @GetMapping
    public List<UserDTO> getUsers(@RequestParam(name = "email", required = false) String email) {
        return userService.getUsers(email);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody UserDTO userDTO) {
        userService.createUser(userDTO.parseUser());
    }

    @PreAuthorize("#id == authentication.principal.id")
    @PostMapping("/{id}/change_password")
    public UserTokenEntity changePassword(HttpServletRequest httpServletRequest, @Valid @RequestBody ChangePasswordDTO changePasswordDTO, @PathVariable("id") Long id) {
        userService.changePassword(httpServletRequest, changePasswordDTO);
        String authToken = tokenHelper.getToken(httpServletRequest);
        String refreshedToken = tokenHelper.refreshToken(authToken);
        return new UserTokenEntity(refreshedToken);
    }

    @PostMapping("/reset_password/{email}")
    public void resetPassword(@PathVariable("email") String email) {
        userService.generatePasswordResetToken(email);
    }

    @PostMapping(value = "/{id}/reset_password/{token}")
    public void confirmResetPassword(@PathVariable("id") Long id, @PathVariable("token") String token) {
        userService.validatePasswordResetToken(id, token);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PutMapping("/{id}")
    public void updateOrAddUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
        userService.updateOrAddUser(userDTO.parseUser(), id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserFromDatabase(id);
    }
}
