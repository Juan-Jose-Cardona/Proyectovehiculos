package co.edu.usbcali.vehiculosnotificacion.dto.request;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CreateObligationRuleNotifyDayRequest {

    //atributos que se van a pedir

    private Integer obligationRuleId;
    private Integer notifyDay;


}
