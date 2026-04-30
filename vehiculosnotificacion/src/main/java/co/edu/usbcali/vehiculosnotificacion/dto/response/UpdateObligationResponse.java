package co.edu.usbcali.vehiculosnotificacion.dto.response;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ObligationStatus;
import co.edu.usbcali.vehiculosnotificacion.model.enums.ObligationType;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;


@Getter
@Builder
public class UpdateObligationResponse {

    private Integer id;
    private String vehiclePlate;
    private ObligationType type;
    private LocalDate dueDate;
    private ObligationStatus status;
    private Timestamp lastCalcAt;
    private String notes;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
