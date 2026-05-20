package co.edu.usbcali.vehiculosnotificacion.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class CreateVehicleRequest {

    private Integer userId;
    private String plate;
    private String brand;
    private String lineModel;
    private Integer modelYear;
    private String vehicleType;
    private String notes;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}
