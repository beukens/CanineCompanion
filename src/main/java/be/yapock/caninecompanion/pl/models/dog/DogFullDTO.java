package be.yapock.caninecompanion.pl.models.dog;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.pl.models.breed.BreedDTO;
import be.yapock.caninecompanion.pl.models.person.PersonShortDTO;
import be.yapock.caninecompanion.pl.models.weight.WeightAllDTO;

import java.time.LocalDate;
import java.util.List;

public record DogFullDTO(
        long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String sex,
        boolean isSterilized,
        BreedDTO breed,
        PersonShortDTO owner,
        List<WeightAllDTO> weighthistory
) {
    public static DogFullDTO fromEntity(Dog dog){
        return new DogFullDTO(
                dog.getId(),
                dog.getFirstName(),
                dog.getLastName(),
                dog.getDateOfBirth(),
                dog.getSex(),
                dog.isSterilized(),
                BreedDTO.fromEntity(dog.getBreed()),
                PersonShortDTO.fromEntity(dog.getOwner()),
                dog.getWeights().stream()
                        .map(WeightAllDTO::fromEntity)
                        .toList()
        );
    }
}
