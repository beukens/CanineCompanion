package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DiagnosticService;
import be.yapock.caninecompanion.pl.models.diagnostic.DiagnosticForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import be.yapock.caninecompanion.pl.models.diagnostic.DiagnosticDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/diagnostic")
public class DiagnosticController {
    private final DiagnosticService diagnosticService;

    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    @PostMapping("/")
    public void createDiagnostic(@RequestBody DiagnosticForm form) {
        diagnosticService.create(form);
    }



    /**
     * Retrieves a specific DiagnosticDTO based on the given ID.
     *
     * @param id the ID of the diagnostic to retrieve
     * @return the ResponseEntity containing the DiagnosticDTO, or a ResponseEntity with no body and a status code indicating the operation failure
     */
    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok(DiagnosticDTO.fromEntity(diagnosticService.getOne(id)));
    }

    /**
     * Retrieves a list of DiagnosticDTO objects based on the given dog ID.
     *
     * @param id the ID of the dog
     * @return the ResponseEntity containing a list of DiagnosticDTO objects, or a ResponseEntity with no body and a status code indicating the operation failure
     */
    @GetMapping("/dog/{id}")
    public ResponseEntity<List<DiagnosticDTO>> getAllByDog(@PathVariable long id) {
        return ResponseEntity.ok(diagnosticService.getAllByDog(id).stream()
                .map(DiagnosticDTO::fromEntity)
                .toList());
    }

    /**
     * Deletes a diagnostic record based on the specified ID.
     *
     * @param id The ID of the diagnostic record to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteDiagnostic(@PathVariable long id) {
        diagnosticService.delete(id);
    }
}
