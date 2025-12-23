package ma.example.scenariosensor.service;

import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.dto.SensorData;
import ma.example.scenariosensor.entity.Measurement;
import ma.example.scenariosensor.entity.MeasurementStatus;
import ma.example.scenariosensor.entity.Severity;
import ma.example.scenariosensor.repository.MeasurementRepository;
import ma.example.scenariosensor.service.EvaluationService;
import ma.example.scenariosensor.service.IncidentService;
import ma.example.scenariosensor.service.RealtimeService;
import ma.example.scenariosensor.service.ScenarioService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorIngestionService {

    private final ScenarioService scenarioService;
    private final EvaluationService evaluationService;
    private final IncidentService incidentService;
    private final MeasurementRepository measurementRepo;
    private final RealtimeService realtimeService;

    public void ingest(SensorData raw) {

        // 1️⃣ Apply scenario
        SensorData contextual = scenarioService.applyScenario(raw);

        // 2️⃣ Evaluate
        MeasurementStatus status = evaluationService.evaluate(contextual);

        // 3️⃣ Build measurement
        Measurement m = Measurement.builder()
                .temperature(contextual.getTemperature())
                .humidity(contextual.getHumidity())
                .status(status)
                .build();

        // 4️⃣ Incident if abnormal
        // 4️⃣ Incident if abnormal
        if (status != MeasurementStatus.OK) {

            String type;
            Severity severity;

            if (status == MeasurementStatus.CRITICAL) {
                type = "HIGH_TEMPERATURE";
                severity = Severity.CRITICAL;
            } else {
                type = "HIGH_TEMPERATURE";
                severity = Severity.WARNING;
            }

            incidentService.create(
                    type,
                    severity,
                    m.getTemperature()
            );
        }


        // 5️⃣ Store
        measurementRepo.save(m);

        // 6️⃣ Push real-time
        realtimeService.push(m);
    }
}
