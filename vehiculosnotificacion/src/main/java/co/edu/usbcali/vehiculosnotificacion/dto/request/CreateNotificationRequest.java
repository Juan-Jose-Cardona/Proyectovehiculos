package co.edu.usbcali.vehiculosnotificacion.dto.request;


import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import co.edu.usbcali.vehiculosnotificacion.model.enums.NotificationKind;
import co.edu.usbcali.vehiculosnotificacion.model.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotificationRequest {

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
