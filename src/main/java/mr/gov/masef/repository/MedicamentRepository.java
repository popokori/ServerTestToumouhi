package mr.gov.masef.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import mr.gov.masef.entites.Medicament;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
}
