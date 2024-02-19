package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    List<UserToken> findAllByTokenOrderByEmitAtDesc(String token);
}
