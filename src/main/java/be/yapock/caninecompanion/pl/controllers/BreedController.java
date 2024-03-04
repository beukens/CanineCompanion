package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.BreedService;
import be.yapock.caninecompanion.pl.models.breed.BreedDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/breed")
public class BreedController {
    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreedDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok(BreedDTO.fromEntity(breedService.getOne(id)));
    }
}
