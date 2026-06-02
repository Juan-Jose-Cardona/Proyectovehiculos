package co.edu.usbcali.vehiculosnotificacion.dto.request;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ObligationStatus;
import co.edu.usbcali.vehiculosnotificacion.model.enums.ObligationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

//valids
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateObligationRequest {

    //atributos a actualizar

    //valida vehiculo positivo
    @Positive(message = "El vehicleId debe ser mayor a 0")
    private Integer vehicleId;

    private ObligationType type;

    //valida fecha futura
    @FutureOrPresent(message = "La fecha debe ser actual o futura")
    private LocalDate dueDate;

    private ObligationStatus status;

    private Timestamp lastCalcAt;

    //valida notas tamaño
    @Size(max = 2000, message = "Las notas soportan hasta 2000 caracteres")
    private String notes;


}
