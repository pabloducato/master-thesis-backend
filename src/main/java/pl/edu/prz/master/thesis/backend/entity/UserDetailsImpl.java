package pl.edu.prz.master.thesis.backend.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.prz.master.thesis.backend.enums.Status;

import java.util.LinkedList;
import java.util.List;

@Getter
public class UserDetailsImpl implements UserDetails {
    private final Long id;
    private final String password;
    private final List<GrantedAuthority> authorities;
    private final boolean enabled;

    public UserDetailsImpl(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.authorities = new LinkedList<>();
        this.authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        this.enabled = (user.getStatus().equals(Status.ACTIVE));
    }

    @Override
    public String getUsername() {
        return Long.toString(id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
