package be.yapock.caninecompanion.pl.models.appointment;

import be.yapock.caninecompanion.dal.models.Appointment;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.pl.models.dog.DogShortDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record AppointmentAllDTO(
        long id,
        LocalDateTime schedulded,
        List<DogShortDTO> dogs
) {
    public static AppointmentAllDTO fromEntity(Appointment appointment){
        return new AppointmentAllDTO(appointment.getId(), appointment.getSchedulded(),appointment.getDogs().stream()
                .map(DogShortDTO::fromEntity)
                .toList());
    }
}
