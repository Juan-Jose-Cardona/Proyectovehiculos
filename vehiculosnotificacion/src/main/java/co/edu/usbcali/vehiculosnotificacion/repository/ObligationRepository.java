package co.edu.usbcali.vehiculosnotificacion.repository;


import co.edu.usbcali.vehiculosnotificacion.model.Obligation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObligationRepository  extends JpaRepository<Obligation, Integer> {
}
