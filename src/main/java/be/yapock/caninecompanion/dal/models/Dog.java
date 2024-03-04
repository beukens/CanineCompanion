package be.yapock.caninecompanion.dal.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter
public class Dog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Setter
    String firstName;
    @Setter
    String lastName;
    @Setter
    LocalDate dateOfBirth;
    @Setter
    String sex;
    @Setter
    boolean isSterilized;

}
