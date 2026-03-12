package co.edu.usbcali.vehiculosnotificacion.repository;

import co.edu.usbcali.vehiculosnotificacion.model.ObligationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObligationRuleRepository extends JpaRepository<ObligationRule, Integer> {
}
