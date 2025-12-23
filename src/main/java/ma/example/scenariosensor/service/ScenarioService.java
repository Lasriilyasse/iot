package ma.example.scenariosensor.service;

import ma.example.scenariosensor.dto.SensorData;
import org.springframework.stereotype.Service;

@Service
public class ScenarioService {

    private boolean scenarioActive = false;
    private double speedFactor = 1.0;

    /** Start stress scenario */
    public void start() {
        scenarioActive = true;
        speedFactor = 1.0;
    }

    /** Stop + reset scenario */
    public void reset() {
        scenarioActive = false;
        speedFactor = 1.0;
    }

    /** Apply scenario effect if active */
    public SensorData applyScenario(SensorData raw) {

        if (!scenarioActive) {
            return raw; // NORMAL MODE
        }

        // simulate acceleration
        speedFactor += 0.15;

        SensorData modified = new SensorData();
        modified.setTemperature(raw.getTemperature() + speedFactor * 2);
        modified.setHumidity(raw.getHumidity() + speedFactor * 0.5);

        return modified;
    }

    public boolean isScenarioActive() {
        return scenarioActive;
    }
}
