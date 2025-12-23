package ma.example.scenariosensor.service;

import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.entity.Incident;
import ma.example.scenariosensor.entity.Measurement;
import ma.example.scenariosensor.entity.Severity;
import ma.example.scenariosensor.repository.IncidentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepo;

    public void create(
            String type,
            Severity severity,
            double value
    ) {
        Incident incident = new Incident();
        incident.setType(type);
        incident.setSeverity(severity);
        incident.setValue(value);

        incidentRepo.save(incident);

        // notification hook (email, log, etc.)
        System.out.println("🚨 INCIDENT: " + type + " - " + severity);
    }
}
