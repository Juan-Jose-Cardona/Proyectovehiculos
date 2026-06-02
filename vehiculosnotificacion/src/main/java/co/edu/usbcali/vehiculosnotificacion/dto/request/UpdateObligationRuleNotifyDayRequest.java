package co.edu.usbcali.vehiculosnotificacion.dto.request;

import lombok.Builder;
import lombok.Getter;

//valid
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Getter
@Builder
public class UpdateObligationRuleNotifyDayRequest {

    //atributos que se van a pedir cambiar

    //valida regla positiva
    @Positive(message = "El obligationRuleId debe ser mayor a 0")
    private Integer obligationRuleId;

    //valida dia minimo
    @Min(value = 0, message = "El notifyDay debe ser mayor o igual a 0")
    private Integer notifyDay;

}
