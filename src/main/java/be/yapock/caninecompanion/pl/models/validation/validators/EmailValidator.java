package be.yapock.caninecompanion.pl.models.validation.validators;

import be.yapock.caninecompanion.pl.models.PersonForm;
import be.yapock.caninecompanion.pl.models.validation.constraints.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, PersonForm> {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PersonForm personForm, ConstraintValidatorContext constraintValidatorContext) {
        return personForm.mail() != null && pattern.matcher(personForm.mail()).matches();
    }
}
