package co.edu.usbcali.vehiculosnotificacion.repository;

import co.edu.usbcali.vehiculosnotificacion.model.ObligationRuleNotifyDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObligationRuleNotifyDayRepository extends JpaRepository<ObligationRuleNotifyDay, Integer> {
}
