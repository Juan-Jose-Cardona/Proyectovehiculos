package co.edu.usbcali.vehiculosnotificacion.dto.request;


import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//importa localtime para usar el atributo de su tipo
import java.time.LocalTime;

//valid
import jakarta.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateObligationRuleRequest {

    //valida obligacion positiva
    @Positive(message = "El obligationId debe ser mayor a 0")
    private Integer obligationId;

    private LocalTime sendWindowStart;

    private LocalTime sendWindowEnd;

    private Boolean isEnabled;


}
