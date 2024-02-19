package be.yapock.caninecompanion.pl.config.security;

import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserTokenConfig {
    private static final String SECRET_KEY = "M97cU4YFZv\"Be2Aa`n,ph.8f;$gPb<X>mS{q#DRydK_j6Ck()t";
    private static final long EXPIRES_AT = 86_400_000;
    private final PersonRepository personRepository;


    public UserTokenConfig(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public static String generateToken(String firstName, String lastName){
        return JWT.create()
                .withSubject(firstName)
                .withClaim("lastName", lastName)
                .withExpiresAt(Instant.now().plusMillis(EXPIRES_AT))
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public boolean validateToken(String token){
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                    .build().verify(token);

            String firstName = jwt.getSubject();
            String lastName = String.valueOf(jwt.getClaim("lastName"));
            Person person = personRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(()->new EntityNotFoundException("personne pas trouvée"));
            return true;
        } catch (JWTVerificationException | EntityNotFoundException e){
            return false;
        }
    }
}
