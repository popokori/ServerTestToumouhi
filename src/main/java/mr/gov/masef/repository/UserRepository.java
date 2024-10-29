package mr.gov.masef.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import mr.gov.masef.entites.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    public Optional<User> findByNni(String nni);
}
