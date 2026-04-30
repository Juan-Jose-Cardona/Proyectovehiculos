package co.edu.usbcali.vehiculosnotificacion.dto.request;

import co.edu.usbcali.vehiculosnotificacion.model.Obligation;
import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import co.edu.usbcali.vehiculosnotificacion.model.enums.NotificationKind;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;

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
