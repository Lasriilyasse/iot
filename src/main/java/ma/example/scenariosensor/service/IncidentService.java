package ma.example.scenariosensor.service;

import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.entity.Incident;
import ma.example.scenariosensor.entity.Severity;
import ma.example.scenariosensor.repository.IncidentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepo;
    private final MailService mailService;

    // 🔴 CRITICAL → save + email
    public void create(String type, Severity severity, double value) {

        Incident incident = new Incident();
        incident.setType(type);
        incident.setSeverity(severity);
        incident.setValue(value);

        incidentRepo.save(incident);

        mailService.sendIncidentAlert(
                type,
                severity.name(),
                value
        );

        System.out.println("🚨 CRITICAL INCIDENT: " + type);
    }

    // 🟡 WARNING → save ONLY
    public void createWarningOnly(String type, double value) {

        Incident incident = new Incident();
        incident.setType(type);
        incident.setSeverity(Severity.WARNING);
        incident.setValue(value);

        incidentRepo.save(incident);

        System.out.println("⚠ WARNING logged: " + type);
    }
}
