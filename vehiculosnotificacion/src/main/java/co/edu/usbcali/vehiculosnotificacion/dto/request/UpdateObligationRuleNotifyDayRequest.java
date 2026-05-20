package co.edu.usbcali.vehiculosnotificacion.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateObligationRuleNotifyDayRequest {

    //atributos que se van a pedir cambiar
    private Integer obligationRuleId;
    private Integer notifyDay;

}
