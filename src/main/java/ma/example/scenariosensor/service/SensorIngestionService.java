package ma.example.scenariosensor.service;

import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.dto.SensorData;
import ma.example.scenariosensor.entity.Measurement;
import ma.example.scenariosensor.entity.MeasurementStatus;
import ma.example.scenariosensor.entity.Severity;
import ma.example.scenariosensor.repository.MeasurementRepository;
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

        // 1️⃣ Apply scenario (acceleration, stress, etc.)
        SensorData contextual = scenarioService.applyScenario(raw);

        // 2️⃣ Evaluate FIRST (pure logic, fast)
        MeasurementStatus status = evaluationService.evaluate(contextual);

        // 3️⃣ Build measurement WITH final status
        Measurement m = Measurement.builder()
                .temperature(contextual.getTemperature())
                .humidity(contextual.getHumidity())
                .status(status)
                .build();

        // 4️⃣ If abnormal → create incident (SIDE EFFECT)
        // 4️⃣ Incident handling (SIDE EFFECTS)
        if (status == MeasurementStatus.CRITICAL) {

            incidentService.create(
                    "HIGH_TEMPERATURE",
                    Severity.CRITICAL,
                    m.getTemperature()
            );

        } else if (status == MeasurementStatus.WARNING) {

            // OPTIONAL: store incident but NO email
            incidentService.createWarningOnly(
                    "HIGH_TEMPERATURE",
                    m.getTemperature()
            );
        }


        // 5️⃣ STORE (single source of truth)
        Measurement saved = measurementRepo.save(m);

        // 6️⃣ Real-time push AFTER store
        realtimeService.push(saved);
    }
}
