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
import java.util.stream.Collectors;

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
    @Column(nullable = false)
    @Getter @Setter
    private String password;
    @Getter @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(length = 50,nullable = false)
    private List<UserRole> userRole;
    @Setter
    private boolean isEnabled = true;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_"+userRole))
                .collect(Collectors.toList());
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
        return isEnabled;
    }
}
