package co.edu.usbcali.vehiculosnotificacion.dto.request;


import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import co.edu.usbcali.vehiculosnotificacion.model.enums.NotificationKind;
import co.edu.usbcali.vehiculosnotificacion.model.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.LocalDate;

//imprta para valid
import jakarta.validation.constraints.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotificationRequest {

    //valida usuario requerido
    @NotNull(message = "El userId es requerido")
    @Positive(message = "El userId debe ser mayor a 0")
    private Integer userId;

    //valida vehiculo requerido
    @NotNull(message = "El vehicleId es requerido")
    @Positive(message = "El vehicleId debe ser mayor a 0")
    private Integer vehicleId;

    //valida obligacion requerida
    @NotNull(message = "El obligationId es requerido")
    @Positive(message = "El obligationId debe ser mayor a 0")
    private Integer obligationId;

    //valida canal requerido
    @NotNull(message = "El canal es requerido")
    private ChannelType channel;

    //valida tipo requerido
    @NotNull(message = "El tipo es requerido")
    private NotificationKind kind;

    //valida dias positivos
    @Min(value = 0, message = "Los dias deben ser mayor o igual a 0")
    private Integer daysBeforeDue;

    //valida fecha requerida
    @NotNull(message = "La fecha de vencimiento es requerida")
    private LocalDate dueDate;

    //valida programacion requerida
    @NotNull(message = "La fecha programada es requerida")
    private Timestamp scheduledFor;

    private String payloadJson;

    //valida estado requerido
    @NotNull(message = "El estado es requerido")
    private NotificationStatus status;

    //valida intentos positivos
    @Min(value = 0, message = "Los intentos deben ser mayor o igual a 0")
    private Integer attemptCount;

    private String lastError;

    //valida bloqueo opcional
    @Size(max = 100, message = "El lockedBy soporta hasta 100 caracteres")
    private String lockedBy;

    private Timestamp lockedAt;

    private Timestamp sentAt;


}
