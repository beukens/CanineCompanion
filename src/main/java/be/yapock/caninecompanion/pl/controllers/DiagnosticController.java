package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DiagnosticService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diagnostic")
public class DiagnosticController {
    private final DiagnosticService diagnosticService;

    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }
}
