package mr.gov.masef.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mr.gov.masef.entites.Hopital;

public interface HopitalRepository extends JpaRepository<Hopital,Long>  {
    List<Hopital> findByWilaya(String wilaya);
}
