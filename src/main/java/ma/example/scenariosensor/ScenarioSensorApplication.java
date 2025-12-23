package ma.example.scenariosensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ScenarioSensorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScenarioSensorApplication.class, args);
    }

}
