package co.edu.usbcali.vehiculosnotificacion.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class UpdateVehicleResponse {

    private Integer userId;
    private String plate;
    private String brand;
    private String lineModel;
    private Integer modelYear;
    private String vehicleType;
    private String notes;

}
