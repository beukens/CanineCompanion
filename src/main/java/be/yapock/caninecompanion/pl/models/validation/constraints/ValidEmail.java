package be.yapock.caninecompanion.pl.models.validation.constraints;

import be.yapock.caninecompanion.pl.models.validation.validators.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "Adresse e-mail invalide";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
