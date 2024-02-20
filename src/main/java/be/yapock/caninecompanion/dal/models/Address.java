package be.yapock.caninecompanion.dal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "number")
    private int number;
    @Column(name="box")
    private String box;
    @Column(name = "street")
    private String street;
    @Column(name = "zip")
    private int zip;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
