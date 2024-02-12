package be.yapock.caninecompanion.dal.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor
public class Person {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter @Setter
    @Column(length = 50, nullable = false)
    private String firstName;
    @Getter @Setter
    @Column(length = 50, nullable = false)
    private String lastName;
    @Getter @Setter
    @Column(length = 50, nullable = false)
    private String email;
    @Getter @Setter
    @Column(length = 12, nullable = false)
    private String phoneNumber;
    @Getter @Setter
    @Column(nullable = false)
    private char gender;
}
