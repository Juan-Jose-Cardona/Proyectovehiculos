package co.edu.usbcali.vehiculosnotificacion.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class UpdateVehicleResponse {

    //atributos a actualizar

    private Integer id;
    private Integer userId;
    private String userName;
    private String plate;
    private String brand;
    private String lineModel;
    private Integer modelYear;
    private String vehicleType;
    private String notes;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}