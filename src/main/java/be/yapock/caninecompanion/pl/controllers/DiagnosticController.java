package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DiagnosticService;
import be.yapock.caninecompanion.pl.models.diagnostic.DiagnosticForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
