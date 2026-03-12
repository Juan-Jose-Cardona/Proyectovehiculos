package co.edu.usbcali.vehiculosnotificacion.repository;

import co.edu.usbcali.vehiculosnotificacion.model.NotificationAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationAttemptRepository extends JpaRepository<NotificationAttempt, Long> {
}
