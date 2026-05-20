package co.edu.usbcali.vehiculosnotificacion.dto.response;


import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import java.sql.Timestamp;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateObligationRuleResponse {

    //atributos a actualizarr

    private Integer id;
    private Integer obligationId;
    //private Integer[] notifyDays;
    //private ChannelType[] channels;
    private LocalTime sendWindowStart;
    private LocalTime sendWindowEnd;
    private Boolean isEnabled;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
