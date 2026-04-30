package co.edu.usbcali.vehiculosnotificacion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleRequest {

    private Integer userId;
    private String plate;
    private String brand;
    private String lineModel;
    private Integer modelYear;
    private String vehicleType;
    private String notes;

}