package be.yapock.caninecompanion.dal.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Weight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double weight;
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;
}
