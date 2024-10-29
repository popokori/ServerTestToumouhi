package mr.gov.masef.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mr.gov.masef.entites.Hopital;
import mr.gov.masef.entites.SeanceDialyse;
import mr.gov.masef.entites.User;
import mr.gov.masef.enums.EnumEtatSeanceDialyse;

public interface SeanceDialyseRepository extends JpaRepository<SeanceDialyse, Long> {
  public SeanceDialyse findByUserAndEtat(User user,EnumEtatSeanceDialyse etat);
  public List<SeanceDialyse> findByHopitalAndEtat(Hopital hopital,EnumEtatSeanceDialyse etat);
  // Recherche toutes les séances de dialyse par utilisateur et état (si nécessaire)
  public List<SeanceDialyse> findAllByUserAndEtat(User user, EnumEtatSeanceDialyse etat);
}
