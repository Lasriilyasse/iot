package ma.example.scenariosensor.controller;


import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.dto.SensorData;
import ma.example.scenariosensor.service.SensorIngestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorIngestionService ingestionService;

    @PostMapping("/data")
    public void receive(@RequestBody SensorData data) {
        ingestionService.ingest(data);
    }
}
