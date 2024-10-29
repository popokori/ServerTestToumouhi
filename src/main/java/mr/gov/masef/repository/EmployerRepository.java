package mr.gov.masef.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mr.gov.masef.entites.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
	public List<Employer> findByWilaya(String wilaya);
	public Employer findByUserName(String username);
}
