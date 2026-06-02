package co.edu.usbcali.vehiculosnotificacion.dto.request;


import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//valids
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;



@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateObligationRuleChannelRequest {

    // Atributos de la clase

    //atributos que se van a pedir
    @NotNull(message = "El obligationRuleId es requerido")
    @Positive(message = "El obligationRuleId debe ser mayor a 0")
    private Integer obligationRuleId;

    //valida canal requerido
    @NotNull(message = "El channel es requerido")
    private ChannelType channel;

}
