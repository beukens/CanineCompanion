package be.yapock.caninecompanion.dal.models;

import be.yapock.caninecompanion.dal.models.enums.DogSize;
import be.yapock.caninecompanion.dal.models.enums.RaceGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private RaceGroup raceGroup;
    private DogSize size;
    private String temperament;
}
