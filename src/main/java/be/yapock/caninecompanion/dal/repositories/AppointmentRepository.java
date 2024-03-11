package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByScheduldedAfter(LocalDateTime time);
    List<Appointment> findAllByOwner_Id(long id);
}
