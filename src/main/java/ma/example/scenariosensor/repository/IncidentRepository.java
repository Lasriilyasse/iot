package ma.example.scenariosensor.repository;


import ma.example.scenariosensor.entity.Incident;
import ma.example.scenariosensor.entity.IncidentType;
import ma.example.scenariosensor.entity.Severity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IncidentRepository extends JpaRepository<Incident, Long> {


    boolean existsByTypeAndSeverityAndDetectedAtAfter(
            String type,
            Severity severity,
            LocalDateTime after
    );
}
