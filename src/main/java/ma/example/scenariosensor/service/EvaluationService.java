package ma.example.scenariosensor.service;

import ma.example.scenariosensor.dto.SensorData;
import ma.example.scenariosensor.entity.MeasurementStatus;
import ma.example.scenariosensor.entity.Severity;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {

    public MeasurementStatus evaluate(SensorData data) {

        if (data.getTemperature() >= 45 || data.getHumidity() >= 85)
            return MeasurementStatus.CRITICAL;

        if (data.getTemperature() >= 35 || data.getHumidity() >= 70)
            return MeasurementStatus.WARNING;

        return MeasurementStatus.OK;
    }

    public Severity toSeverity(MeasurementStatus status) {

        return switch (status) {
            case WARNING -> Severity.WARNING;
            case CRITICAL -> Severity.CRITICAL;
            default -> null; // NORMAL → no incident
        };
    }
}
