package co.edu.usbcali.vehiculosnotificacion.repository;

import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    boolean existsByUserIdAndPlate(Integer userId, String plate);

}
