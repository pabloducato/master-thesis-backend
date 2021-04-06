package pl.edu.prz.master.thesis.backend.security.auth;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.edu.prz.master.thesis.backend.entity.User;
import pl.edu.prz.master.thesis.backend.repository.UserRepository;
import pl.edu.prz.master.thesis.backend.security.TokenHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenHelper tokenHelper;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    public TokenAuthenticationFilter(TokenHelper tokenHelper, UserDetailsService userDetailsService, UserRepository userRepository, AuthenticationEntryPoint authenticationEntryPoint) {
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userRepository = userRepository;
    }

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        if (!(request.getRequestURI().equals("/auth/login") || request.getRequestURI().contains("/reset_password"))) {
            String token = tokenHelper.getToken(request);
            if (token != null) {
                Long id;
                try {
                    id = getIdFromToken(token);
                    User user = userRepository.findById(id).get();
                    Date issuedDate = tokenHelper.getIssuedAtFromToken(token);
                    Date lastPasswordModified = user.getLastPasswordModified();
                    if (lastPasswordModified != null && issuedDate.before(lastPasswordModified))
                        throw new AuthenticationServiceException("Password was changed and token is out of date.");
                    String email = user.getEmail();
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    chain.doFilter(request, response);
                } catch (AuthenticationException ex) {
                    authenticationEntryPoint.commence(request, response, ex);
                }
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private Long getIdFromToken(String token) throws AuthenticationException {
        try {
            return tokenHelper.getIdFromToken(token);
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Failed to parse authentication token.", ex);
        }
    }
}
