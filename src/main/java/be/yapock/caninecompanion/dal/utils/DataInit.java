package be.yapock.caninecompanion.dal.utils;

import be.yapock.caninecompanion.dal.models.Address;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.models.User;
import be.yapock.caninecompanion.dal.models.enums.UserRole;
import be.yapock.caninecompanion.dal.repositories.AddressRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.dal.repositories.UserRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.method.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Locale;

@Component
public class DataInit implements InitializingBean {
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${api.data-init}")
    private boolean insertion;

    public DataInit(AddressRepository addressRepository, PersonRepository personRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
                    .build();
            personRepository.save(person);
            for (int i = 0; i < 10; i++) {
                Address address = Address.builder()
                        .street(faker.address().streetAddress())
                        .city(faker.address().city())
                        .zip(Integer.parseInt(faker.address().zipCode()))
                        .number(faker.number().positive())
                        .box(faker.address().mailBox())
                        .country(faker.address().country())
                        .build();
                addressRepository.save(address);
            }
            for (int i = 0; i < 10; i++) {
                person = Person.builder()
                        .email(faker.internet().emailAddress())
                        .gender(faker.gender().shortBinaryTypes())
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .phoneNumber(faker.phoneNumber().phoneNumberInternational())
                        .address(addressRepository.findById((long) (i+1)).get())
                        .build();
                personRepository.save(person);
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
