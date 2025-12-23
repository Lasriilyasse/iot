package ma.example.scenariosensor.service;

import ma.example.scenariosensor.dto.SensorData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SensorFetchService {

    private final RestTemplate restTemplate = new RestTemplate();

    // 👉 use mDNS OR IP
    private static final String SENSOR_URL =
            "http://esp32.local/api/sensors";
    // OR: "http://192.168.1.120/api/sensors"

    public SensorData fetch() {
        return restTemplate.getForObject(SENSOR_URL, SensorData.class);
    }
}
