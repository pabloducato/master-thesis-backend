package pl.edu.prz.master.thesis.backend.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.entity.UserTokenEntity;
import pl.edu.prz.master.thesis.backend.security.TokenHelper;
import pl.edu.prz.master.thesis.backend.security.auth.JwtAuthenticationRequest;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/auth", produces = APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final TokenHelper tokenHelper;

    @Lazy
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(TokenHelper tokenHelper, AuthenticationManager authenticationManager) {
        this.tokenHelper = tokenHelper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public UserTokenEntity createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest
    ) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = tokenHelper.generateToken(user.getUsername(), user.getAuthorities());
        return new UserTokenEntity(token);
    }

    @GetMapping("/refresh")
    public UserTokenEntity refreshAuthenticationToken(
            HttpServletRequest request
    ) {
        String authToken = tokenHelper.getToken(request);
        String refreshedToken = tokenHelper.refreshToken(authToken);
        return new UserTokenEntity(refreshedToken);
    }
}
