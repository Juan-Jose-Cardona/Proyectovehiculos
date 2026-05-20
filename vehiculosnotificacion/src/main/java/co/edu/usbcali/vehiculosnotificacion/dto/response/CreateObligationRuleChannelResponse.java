package co.edu.usbcali.vehiculosnotificacion.dto.response;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CreateObligationRuleChannelResponse {

    //lo ideal es que esta clase sea inmutable

    private Integer id;
    private Integer obligationRuleId;
    private ChannelType channel;

}
