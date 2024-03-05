package be.yapock.caninecompanion.dal.utils;

import be.yapock.caninecompanion.dal.models.Breed;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.models.User;
import be.yapock.caninecompanion.dal.models.enums.DogSize;
import be.yapock.caninecompanion.dal.models.enums.RaceGroup;
import be.yapock.caninecompanion.dal.models.enums.UserRole;
import be.yapock.caninecompanion.dal.repositories.BreedRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.dal.repositories.UserRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Locale;

@Component
public class DataInit implements InitializingBean {
    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DogRepository dogRepository;
    private final BreedRepository breedRepository;

    @Value("${api.data-init}")
    private boolean insertion;

    public DataInit(PersonRepository personRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, DogRepository dogRepository, BreedRepository breedRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.dogRepository = dogRepository;
        this.breedRepository = breedRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(insertion){
            Faker faker = new Faker(Locale.FRANCE);
            Person person = Person.builder()
                    .email("kerexig931@molyg.com")
                    .firstName("jsaispas")
                    .lastName("ikkweetniet")
                    .phoneNumber("0123456")
                    .gender("M")
                    .street(faker.address().streetAddress())
                    .city(faker.address().city())
                    .zip(Integer.parseInt(faker.address().zipCode()))
                    .number(faker.number().positive())
                    .box(faker.address().mailBox())
                    .country(faker.address().country())
                    .build();
            personRepository.save(person);
            for (int i = 0; i < 50; i++) {
                person = Person.builder()
                        .email(faker.internet().emailAddress())
                        .gender(faker.gender().shortBinaryTypes())
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .phoneNumber(faker.phoneNumber().phoneNumberInternational())
                        .street(faker.address().streetAddress())
                        .city(faker.address().city())
                        .zip(Integer.parseInt(faker.address().zipCode()))
                        .number(faker.number().positive())
                        .box(faker.address().mailBox())
                        .country(faker.address().country())
                        .build();
                personRepository.save(person);
            }
            for (int i = 0; i < 10; i++) {
                Breed breed = Breed.builder()
                        .raceGroup(RaceGroup.HOUND_BLOODHOUND)
                        .name(faker.dog().breed())
                        .size(DogSize.BIG)
                        .temperament(faker.dog().memePhrase())
                        .build();
                breedRepository.save(breed);
            }
            for (int i = 0; i < 10; i++) {
                Dog dog = Dog.builder()
                        .firstName(faker.dog().name())
                        .lastName(faker.dog().sound())
                        .sex(faker.dog().gender())
                        .owner(personRepository.findById(1L).get())
                        .isSterilized(faker.random().nextBoolean())
                        .dateOfBirth(LocalDate.now())
                        .breed(breedRepository.findById((i+1L)).get())
                        .build();
                dogRepository.save(dog);
            }

            User user = User.builder()
                    .username("admin")
                    .isEnabled(true)
                    .password(passwordEncoder.encode("admin"))
                    .userRole(Collections.singletonList(UserRole.ADMIN))
                    .build();
            userRepository.save(user);
        }
    }
}
