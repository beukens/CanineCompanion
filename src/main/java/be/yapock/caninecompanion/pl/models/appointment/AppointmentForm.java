package be.yapock.caninecompanion.pl.models.appointment;

import java.time.LocalDateTime;
import java.util.List;

public record AppointmentForm(
        LocalDateTime scheduled,
        String comment,
        List<Long> dogIds
) {
}
