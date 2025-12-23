package ma.example.scenariosensor.service;

import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.entity.User;
import ma.example.scenariosensor.service.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final UserService userService;

    /**
     * Send incident notification to ALL users
     */
    public void sendIncidentAlert(
            String incidentType,
            String severity,
            double value
    ) {
        List<User> users = userService.getAllUsers();

        for (User user : users) {

            if (user.getEmail() == null || user.getEmail().isBlank()) {
                continue;
            }

            String subjectPrefix =
                    severity.equals("CRITICAL") ? "🚨 ALERTE CRITIQUE" : "⚠️ Avertissement";

            String body =
                    "Bonjour " + user.getUsername() + ",\n\n" +

                            "Un événement anormal a été détecté par le système de supervision.\n\n" +

                            "📌 Détails de l’incident :\n" +
                            "• Type : " + incidentType.replace("_", " ") + "\n" +
                            "• Niveau de gravité : " + severity + "\n" +
                            "• Valeur mesurée : " + String.format("%.2f", value) + "\n\n" +

                            (severity.equals("CRITICAL")
                                    ? "❗ Action requise immédiatement :\n" +
                                    "La valeur mesurée dépasse le seuil critique autorisé. " +
                                    "Veuillez intervenir sans délai afin d’éviter tout risque matériel ou opérationnel.\n\n"
                                    : "ℹ️ Information :\n" +
                                    "La valeur mesurée dépasse le seuil normal, sans atteindre un niveau critique. " +
                                    "Une surveillance est recommandée.\n\n") +

                            "Ce message a été généré automatiquement par le système de surveillance.\n\n" +
                            "Cordialement,\n" +
                            "— Système de Monitoring IoT";

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject(subjectPrefix + " – Surveillance Capteurs");
            message.setText(body);

            mailSender.send(message);
        }
    }
}
