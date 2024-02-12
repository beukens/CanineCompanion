package be.yapock.caninecompanion.pl.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @Builder
public class Error {
    private String message;
    private HttpStatus status;
    private LocalDateTime requestMadeAt;
    private String URI;
}
