package mr.gov.masef.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mr.gov.masef.entites.Ordonnance;

//Repository pour Ordonnance
public interface OrdonnanceRepository extends JpaRepository<Ordonnance, Long> {
}
