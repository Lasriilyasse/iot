package ma.example.scenariosensor.service;

import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.dto.SensorData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorPollingService {

    private final SensorFetchService sensorFetchService;
    private final SensorIngestionService sensorIngestionService;

    @Scheduled(fixedRate = 2000) // same as ESP32 read
    public void poll() {
        SensorData data = sensorFetchService.fetch();
        if (data != null) {
            sensorIngestionService.ingest(data);
        }
    }
}
