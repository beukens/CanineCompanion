package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DogService;
import be.yapock.caninecompanion.pl.models.dog.DogShortDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    /**
     * Finds all dogs owned by the specified owner.
     *
     * @param id the ID of the owner
     * @return a ResponseEntity containing a list of DogShortDTO objects
     */
    @GetMapping("/all/{id}")
    public ResponseEntity<List<DogShortDTO>> findAllByOwner(@PathVariable long id){
        return ResponseEntity.ok(dogService.findAllByOwner(id).stream()
                .map(DogShortDTO::fromEntity)
                .toList());
    }
}
