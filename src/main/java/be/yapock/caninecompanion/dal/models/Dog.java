package be.yapock.caninecompanion.dal.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter
public class Dog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private LocalDate dateOfBirth;
    @Setter
    private String sex;
    @Setter
    private boolean isSterilized;
    @Setter
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person owner;
    @ManyToOne
    @Setter
    @JoinColumn(name = "breed_id")
    private Breed breed;
    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Weight> weights = new ArrayList<>();
}
