package be.yapock.caninecompanion.dal.models;

import be.yapock.caninecompanion.dal.models.enums.Disease;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Disease disease;
    private LocalDate lastBooster;
    private double frequencies;
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;
}
