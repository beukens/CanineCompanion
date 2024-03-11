package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DiagnosticService;
import be.yapock.caninecompanion.pl.models.diagnostic.DiagnosticDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diagnostic")
public class DiagnosticController {
    private final DiagnosticService diagnosticService;

    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
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
}
