package be.yapock.caninecompanion.pl.models.appointment;

import be.yapock.caninecompanion.dal.models.Appointment;
import be.yapock.caninecompanion.pl.models.dog.DogShortDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record AppointmentOneDTO(
        long id,
        LocalDateTime schedulded,
        LocalTime start,
        LocalTime end,
        boolean firstMeeting,
        String comment,
        List<DogShortDTO> dogs
) {
    public static AppointmentOneDTO fromEntity(Appointment appointment){
        return new AppointmentOneDTO(appointment.getId(),appointment.getSchedulded(),appointment.getStart(),appointment.getEnd(), appointment.isFirstMeeting(), appointment.getComment(), appointment.getDogs().stream()
                .map(DogShortDTO::fromEntity)
                .toList());
    }
}
