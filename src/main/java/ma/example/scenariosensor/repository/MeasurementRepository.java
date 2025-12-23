package ma.example.scenariosensor.repository;


import ma.example.scenariosensor.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
