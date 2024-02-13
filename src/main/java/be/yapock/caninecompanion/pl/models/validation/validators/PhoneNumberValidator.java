package be.yapock.caninecompanion.pl.models.validation.validators;

import be.yapock.caninecompanion.pl.models.person.PersonForm;
import be.yapock.caninecompanion.pl.models.validation.constraints.ValidPhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, PersonForm> {
    private static final String PHONE_REGEX = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    private static final Pattern pattern = Pattern.compile(PHONE_REGEX);
    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PersonForm personForm, ConstraintValidatorContext constraintValidatorContext) {
        return personForm.phoneNumber() != null && pattern.matcher(personForm.phoneNumber()).matches();
    }
}
