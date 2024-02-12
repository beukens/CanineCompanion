package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.pl.models.Error;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler({UsernameNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<Error> handleUsernameNotFoundException(RuntimeException e, HttpServletRequest req){
        Error error = Error.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .requestMadeAt(LocalDateTime.now())
                .URI(req.getRequestURI())
                .build();
        return ResponseEntity.status(error.getStatus())
                .body(error);
    }
}
