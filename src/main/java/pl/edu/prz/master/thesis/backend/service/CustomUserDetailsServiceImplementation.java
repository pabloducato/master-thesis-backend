package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.entity.User;
import pl.edu.prz.master.thesis.backend.entity.UserDetailsImpl;
import pl.edu.prz.master.thesis.backend.repository.UserRepository;

@Service
@Slf4j
@AllArgsConstructor
public class CustomUserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with email '%s'.", email)));
        return new UserDetailsImpl(user);
    }

}
