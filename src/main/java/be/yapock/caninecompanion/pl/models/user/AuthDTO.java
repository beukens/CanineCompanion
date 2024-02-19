package be.yapock.caninecompanion.pl.models.user;

public record AuthDTO(String token,
                      String username,
                      java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> userRoles) {
}
