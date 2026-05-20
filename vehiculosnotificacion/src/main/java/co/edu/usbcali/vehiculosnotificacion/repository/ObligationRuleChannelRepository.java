package co.edu.usbcali.vehiculosnotificacion.repository;

import co.edu.usbcali.vehiculosnotificacion.model.ObligationRuleChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObligationRuleChannelRepository extends JpaRepository<ObligationRuleChannel, Integer> {
}
