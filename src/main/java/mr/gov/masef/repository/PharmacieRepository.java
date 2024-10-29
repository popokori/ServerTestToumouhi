package mr.gov.masef.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mr.gov.masef.entites.Pharmacie;

public interface PharmacieRepository extends JpaRepository<Pharmacie, Long> {
}