package ma.example.scenariosensor.controller;

import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.service.ScenarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scenario")
@RequiredArgsConstructor
public class ScenarioController {

    private final ScenarioService scenarioService;

    @PostMapping("/start")
    public void start() {
        scenarioService.start();
    }

    @PostMapping("/reset")
    public void reset() {
        scenarioService.reset();
    }

    @GetMapping("/status")
    public boolean status() {
        return scenarioService.isScenarioActive();
    }
}
