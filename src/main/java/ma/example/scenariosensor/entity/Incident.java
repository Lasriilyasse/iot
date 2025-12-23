package ma.example.scenariosensor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.example.scenariosensor.entity.Severity;


import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
@Getter
@Setter
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // HIGH_TEMPERATURE, HIGH_HUMIDITY

    @Enumerated(EnumType.STRING)
    private Severity severity;

    private double value;

    private LocalDateTime detectedAt;

    @PrePersist
    void onCreate() {
        detectedAt = LocalDateTime.now();
    }
}
