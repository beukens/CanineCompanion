package be.yapock.caninecompanion.pl.models.user;

import be.yapock.caninecompanion.dal.models.enums.UserRole;

import java.util.List;

public record AuthDTO(String token,
                      String username,
                      List<UserRole> userRoles) {
}
