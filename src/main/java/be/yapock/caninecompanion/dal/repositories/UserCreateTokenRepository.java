package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.UserCreateToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCreateTokenRepository extends JpaRepository<UserCreateToken, Long> {
    List<UserCreateToken> findAllByTokenOrderByEmitAtDesc(String token);
}
