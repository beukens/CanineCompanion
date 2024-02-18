package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.UserCreateToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCreateTokenRepository extends JpaRepository<UserCreateToken, Long> {
}
