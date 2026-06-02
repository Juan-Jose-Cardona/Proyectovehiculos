package co.edu.usbcali.vehiculosnotificacion.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

//validaciones
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateObligationRuleRequest {

    //valida obligacion requerida
    @NotNull(message = "El obligationId es requerido")
    @Positive(message = "El obligationId debe ser mayor a 0")
    private Integer obligationId;

    //valida hora inicio requerida
    @NotNull(message = "La hora inicial es requerida")
    private LocalTime sendWindowStart;

    //valida hora final requerida
    @NotNull(message = "La hora final es requerida")
    private LocalTime sendWindowEnd;

    //valida estado requerido
    @NotNull(message = "El isEnabled es requerido")
    private Boolean isEnabled;


}
