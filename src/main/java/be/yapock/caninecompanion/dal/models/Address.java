package be.yapock.caninecompanion.dal.models;

import jakarta.persistence.*;
import lombok.*;

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
    @Setter
    private boolean isDeleted;
}
