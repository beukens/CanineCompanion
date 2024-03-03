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
    @Column(length = 50, nullable = false)
    private String phoneNumber;
    @Getter @Setter
    @Column(nullable = false)
    private String gender;
    @Column(name= "number")
    @Getter @Setter
    private int number;
    @Column(name="box")
    @Getter @Setter
    private String box;
    @Getter @Setter
    @Column(name = "street")
    private String street;
    @Getter @Setter
    @Column(name = "zip")
    private int zip;
    @Getter @Setter
    @Column(name = "city")
    private String city;
    @Getter @Setter
    @Column(name = "country")
    private String country;
}
