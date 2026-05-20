package co.edu.usbcali.vehiculosnotificacion.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateObligationRuleNotifyDayResponse {

    //atributos que devuelve el cambio

    private Integer id;
    private Integer obligationRuleId;
    private Integer notifyDay;

}
