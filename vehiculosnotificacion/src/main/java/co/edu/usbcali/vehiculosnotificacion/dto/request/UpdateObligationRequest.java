package co.edu.usbcali.vehiculosnotificacion.dto.request;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ObligationStatus;
import co.edu.usbcali.vehiculosnotificacion.model.enums.ObligationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateObligationRequest {

    private Integer vehicleId;
    private ObligationType type;
    private LocalDate dueDate;
    private ObligationStatus status;
    private Timestamp lastCalcAt;
    private String notes;

}