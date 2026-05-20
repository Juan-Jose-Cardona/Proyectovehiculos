package co.edu.usbcali.vehiculosnotificacion.dto.response;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UpdateObligationRuleChannelResponse {

    //atributos a actualizar

    private Integer id;
    private Integer obligationRuleId;
    private ChannelType channel;

}
