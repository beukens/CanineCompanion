package be.yapock.caninecompanion.dal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime schedulded;
    private LocalTime start;
    private LocalTime end;
    private boolean firstMeeting;
    @OneToMany
    private List<Dog> dogs;
    @OneToOne
    @JoinColumn(name = "owner_id")
    private Person owner;
}
