package mr.gov.masef.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mr.gov.masef.entites.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByNumTelephoneAndIsReadFalse(String numTelephone);
    List<Notification> findByNumTelephoneOrderByRefDesc(String numTelephone);
    Notification findByRef(long ref);
}
