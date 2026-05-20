package co.edu.usbcali.vehiculosnotificacion.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateObligationRuleNotifyDayResponse {

    //atributos a crear

    private Integer id;
    private Integer obligationRuleId;
    private Integer notifyDay;

}
