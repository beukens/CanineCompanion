package be.yapock.caninecompanion.dal.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter
public class Morphology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String coat;
    @Setter
    private double height;
    @Setter
    private double chestPerimeter;
    @Setter
    private double frontBackProportion;
    @Setter
    private String headMorphology;
    @Setter
    private String notes;
    @ManyToMany
    @Setter
    private List<Breed> breeds;
    @Setter
    @OneToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    public List<Breed> getBreeds() {
        return List.copyOf(this.breeds);
    }
}
