package be.yapock.caninecompanion.dal.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter
public class Dog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private LocalDate dateOfBirth;
    @Setter
    private String sex;
    @Setter
    private boolean isSterilized;
    @Setter
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person owner;

}
