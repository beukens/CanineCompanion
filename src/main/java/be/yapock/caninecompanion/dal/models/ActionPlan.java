package be.yapock.caninecompanion.dal.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ActionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalDate endDate;

    @Setter
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;
    @OneToMany(fetch = FetchType.EAGER)
    @Setter
    private List<Exercice> exercices;

    public List<Exercice> getExercices() {
        return new ArrayList<>(exercices);
    }

    public void addExercices(Exercice exo) {
        this.exercices.add(exo);
    }
}
