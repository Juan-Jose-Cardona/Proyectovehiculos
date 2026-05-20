package co.edu.usbcali.vehiculosnotificacion.dto.request;


import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//importa localtime para usar el atributo de su tipo
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateObligationRuleRequest {

    //atributos a actualizar
    private Integer obligationId;
   // private Integer[] notifyDays;
   // private ChannelType[] channels;
    private LocalTime sendWindowStart;
    private LocalTime sendWindowEnd;
    private Boolean isEnabled;


}
