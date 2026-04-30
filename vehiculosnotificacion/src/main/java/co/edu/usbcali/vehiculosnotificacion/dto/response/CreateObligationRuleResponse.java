package co.edu.usbcali.vehiculosnotificacion.dto.response;


import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import lombok.Builder;
import lombok.Getter;
import java.sql.Timestamp;
import java.time.LocalTime;

@Getter
@Builder
public class CreateObligationRuleResponse {

    private Integer id;
    private Integer obligationId;
    private Integer[] notifyDays;
    private ChannelType[] channels;
    private LocalTime sendWindowStart;
    private LocalTime sendWindowEnd;
    private Boolean isEnabled;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
