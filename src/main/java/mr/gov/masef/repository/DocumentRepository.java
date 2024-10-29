package mr.gov.masef.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mr.gov.masef.entites.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}
