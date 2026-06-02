package co.edu.usbcali.vehiculosnotificacion.dto.request;

import lombok.Builder;
import lombok.Getter;


//valids
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Getter
@Builder
public class CreateObligationRuleNotifyDayRequest {

    //atributos que se van a pedir

    //atributos que se van a pedir
    @NotNull(message = "El obligationRuleId es requerido")
    @Positive(message = "El obligationRuleId debe ser mayor a 0")
    private Integer obligationRuleId;

    //valida dia requerido
    @NotNull(message = "El notifyDay es requerido")
    @Min(value = 0, message = "El notifyDay debe ser mayor o igual a 0")
    private Integer notifyDay;


}
