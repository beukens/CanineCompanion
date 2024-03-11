package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DiagnosticService;
import org.springframework.web.bind.annotation.DeleteMapping;
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
     * Deletes a diagnostic record based on the specified ID.
     *
     * @param id The ID of the diagnostic record to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteDiagnostic(@PathVariable long id) {
        diagnosticService.delete(id);
    }
}
