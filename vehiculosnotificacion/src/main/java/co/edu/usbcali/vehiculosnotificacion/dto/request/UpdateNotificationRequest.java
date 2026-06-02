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

//importa valid
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNotificationRequest {

    //atributos a actualizar

    //valida usuario positivo
    @Positive(message = "El userId debe ser mayor a 0")
    private Integer userId;

    //valida vehiculo positivo
    @Positive(message = "El vehicleId debe ser mayor a 0")
    private Integer vehicleId;

    //valida obligacion positiva
    @Positive(message = "El obligationId debe ser mayor a 0")
    private Integer obligationId;

    private ChannelType channel;

    private NotificationKind kind;

    //valida dias positivos
    @Min(value = 0, message = "Los dias deben ser mayor o igual a 0")
    private Integer daysBeforeDue;

    private LocalDate dueDate;

    private Timestamp scheduledFor;

    private String payloadJson;

    private NotificationStatus status;

    //valida intentos positivos
    @Min(value = 0, message = "Los intentos deben ser mayor o igual a 0")
    private Integer attemptCount;

    private String lastError;

    //valida bloqueo tamaño
    @Size(max = 100, message = "El lockedBy soporta hasta 100 caracteres")
    private String lockedBy;

    private Timestamp lockedAt;

    private Timestamp sentAt;

}
