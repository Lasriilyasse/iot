package ma.example.scenariosensor.service;

import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.dto.SensorData;
import ma.example.scenariosensor.entity.Measurement;
import ma.example.scenariosensor.entity.MeasurementStatus;
import ma.example.scenariosensor.repository.MeasurementRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository repo;

    public void store(SensorData data, String status) {

        Measurement m = new Measurement();
        m.setTemperature(data.getTemperature());
        m.setHumidity(data.getHumidity());
        m.setStatus(MeasurementStatus.valueOf(status));

        repo.save(m);
    }
}
