package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.MorphologyService;
import be.yapock.caninecompanion.dal.models.Breed;
import be.yapock.caninecompanion.dal.models.Morphology;
import be.yapock.caninecompanion.dal.repositories.BreedRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.MorphologyRepository;
import be.yapock.caninecompanion.pl.models.morphology.MorphologyForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MorphologyServiceImpl implements MorphologyService {
    private final MorphologyRepository morphologyRepository;
    private final BreedRepository breedRepository;
    private final DogRepository dogRepository;

    public MorphologyServiceImpl(MorphologyRepository morphologyRepository, BreedRepository breedRepository, DogRepository dogRepository) {
        this.morphologyRepository = morphologyRepository;
        this.breedRepository = breedRepository;
        this.dogRepository = dogRepository;
    }

    /**
     * Retrieves a Morphology record based on the provided ID.
     *
     * @param id The ID of the Morphology record to retrieve.
     * @return The Morphology record with the specified ID.
     * @throws EntityNotFoundException If no Morphology record is found with the specified ID.
     */
    @Override
    public Morphology getOne(long id) {
        return morphologyRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Morphologie pas trouvée"));
    }

    /**
     * Creates a new Morphology record based on the provided MorphologyForm.
     *
     * @param form The MorphologyForm object containing the data to create the record.
     * @throws IllegalArgumentException If the form is null.
     * @throws EntityNotFoundException If the Dog with the specified ID in the form is not found.
     */
    @Override
    public long create(MorphologyForm form) {
        if (form==null) throw new IllegalArgumentException("Form ne peut être null");
        Morphology morphology = Morphology.builder()
                .coat(form.coat())
                .height(form.height())
                .chestPerimeter(form.chestPerimeter())
                .frontBackProportion(form.frontBackProportion())
                .headMorphology(form.headMorphology())
                .notes(form.notes())
                .breeds(breedRepository.findAllById(form.breedId()))
                .build();
        return morphologyRepository.save(morphology).getId();
    }

    /**
     * Deletes a Morphology record with the given ID.
     *
     * @param id The ID of the Morphology record to delete.
     */
    @Override
    public void delete(long id) {
        morphologyRepository.deleteById(id);
    }
}
