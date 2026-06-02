package co.edu.usbcali.vehiculosnotificacion.dto.request;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import lombok.Builder;
import lombok.Getter;

//valid
import jakarta.validation.constraints.Positive;


@Getter
@Builder
public class UpdateObligationRuleChannelRequest {

    //atributos request update

    //valida regla positiva
    @Positive(message = "El obligationRuleId debe ser mayor a 0")
    private Integer obligationRuleId;

    private ChannelType channel;

}
