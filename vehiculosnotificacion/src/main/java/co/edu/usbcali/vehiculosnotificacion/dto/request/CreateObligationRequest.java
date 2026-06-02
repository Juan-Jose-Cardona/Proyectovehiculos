package co.edu.usbcali.vehiculosnotificacion.dto.request;


import co.edu.usbcali.vehiculosnotificacion.model.enums.ObligationStatus;
import co.edu.usbcali.vehiculosnotificacion.model.enums.ObligationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.LocalDate;

//importa validaciones
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateObligationRequest {

    //valida vehiculo requerido
    @NotNull(message = "El vehicleId es requerido")
    @Positive(message = "El vehicleId debe ser mayor a 0")
    private Integer vehicleId;

    //valida tipo requerido
    @NotNull(message = "El tipo es requerido")
    private ObligationType type;

    //valida fecha requerida
    @NotNull(message = "La fecha de vencimiento es requerida")
    @FutureOrPresent(message = "La fecha debe ser actual o futura")
    private LocalDate dueDate;

    //valida estado requerido
    @NotNull(message = "El estado es requerido")
    private ObligationStatus status;

    private Timestamp lastCalcAt;

    private String notes;

}
