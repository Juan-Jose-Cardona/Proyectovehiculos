package co.edu.usbcali.vehiculosnotificacion.dto.response;


import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import co.edu.usbcali.vehiculosnotificacion.model.enums.NotificationKind;
import co.edu.usbcali.vehiculosnotificacion.model.enums.NotificationStatus;
import lombok.Builder;
import lombok.Getter;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Builder
public class CreateNotificationResponse {

    private Integer id;
    private String userName;
    private String vehiclePlate;
    private String obligationType;
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
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
