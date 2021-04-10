package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.entity.UserAccessToken;
import pl.edu.prz.master.thesis.backend.security.TokenComponent;
import pl.edu.prz.master.thesis.backend.security.auth.JwtAuthenticationRequest;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/auth", produces = APPLICATION_JSON_VALUE)
@Api(tags = "Authentication Controller")
public class AuthenticationController {

    private final TokenComponent tokenComponent;

    @Lazy
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(TokenComponent tokenComponent, AuthenticationManager authenticationManager) {
        this.tokenComponent = tokenComponent;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public UserAccessToken createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
            throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = tokenComponent.generateToken(user.getUsername(), user.getAuthorities());
        return new UserAccessToken(token);
    }

    @GetMapping("/refresh")
    public UserAccessToken refreshAuthenticationToken(
            HttpServletRequest request
    ) {
        String authToken = tokenComponent.getToken(request);
        String refreshedToken = tokenComponent.refreshToken(authToken);
        return new UserAccessToken(refreshedToken);
    }
}
