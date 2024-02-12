package be.yapock.caninecompanion.dal.models;

import be.yapock.caninecompanion.dal.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Builder @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "user_")
public class User implements UserDetails {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50, nullable = false)
    @Getter @Setter
    private String username;
    @Column(length = 50, nullable = false)
    @Getter @Setter
    private String password;
    @Column(nullable = false)
    private UserRole userRole;
    @Setter
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE" + userRole));
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

    @Override
    public boolean isEnabled() {
        return false;
    }
}
