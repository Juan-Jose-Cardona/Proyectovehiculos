package co.edu.usbcali.vehiculosnotificacion.dto.request;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import lombok.Builder;
import lombok.Getter;




@Getter
@Builder
public class UpdateObligationRuleChannelRequest {

    //atributos request update

    private Integer obligationRuleId;
    private ChannelType channel;

}
