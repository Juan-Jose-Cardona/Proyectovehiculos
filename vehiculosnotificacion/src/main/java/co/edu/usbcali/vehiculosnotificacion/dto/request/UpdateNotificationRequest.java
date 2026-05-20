package co.edu.usbcali.vehiculosnotificacion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//importa clase para detectar el tipo de dato
import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import co.edu.usbcali.vehiculosnotificacion.model.enums.NotificationKind;
import co.edu.usbcali.vehiculosnotificacion.model.enums.NotificationStatus;

//importa timesamp para los atributos que lo usan
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNotificationRequest {

    //atributos a actualizar

    private Integer userId;
    private Integer vehicleId;
    private Integer obligationId;
    private ChannelType channel;
    private NotificationKind kind;
    private Integer daysBeforeDue;
    private LocalDate dueDate;
    private Timestamp scheduledFor;
    private String payloadJson;
    private NotificationStatus status;
    private Integer attemptCount;
    private String lastError;
    private String lockedBy;
    private Timestamp lockedAt;
    private Timestamp sentAt;

}
