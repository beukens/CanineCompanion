package be.yapock.caninecompanion.dal.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Builder
public class Diagnostic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private int submissivePosition;
    @Column(nullable = false)
    private int withFamiliarHuman;
    @Column(nullable = false)
    private int withStranger;
    @Column(nullable = false)
    private int withDogs;
    @Column(nullable = false)
    private int withOtherAnimals;
    @Column(nullable = false)
    private int stayingAlone;
    @Column(nullable = false)
    private int affrayed;
    @Column(nullable = false)
    private int contactWHumans;
    @Column(nullable = false)
    private int contactWAnimals;
    @Column(nullable = false)
    private int adaptability;
    @Column(nullable = false)
    private int attachement;
    @Column(nullable = false)
    private int separation;
    @Column(nullable = false)
    private int restPlace;
    @Column(nullable = false)
    private int exploration;
    @Column(nullable = false)
    private int tenderness;
    @Column(nullable = false)
    private int vocalize;
    @Column(nullable = false)
    private int jimpOnPeople;
    @Column(nullable = false)
    private int destruct;
    @Column(nullable = false)
    private int scratchesBruises;
    @Column(nullable = false)
    private int excitation;
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;
}
