package co.edu.usbcali.vehiculosnotificacion.dto.request;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotificationAttemptRequest {

    //valida notificacion requerida
    @NotNull(message = "El notificationId es requerido")
    @Positive(message = "El notificationId debe ser mayor a 0")
    private Integer notificationId;

    //valida numero de intento
    @NotNull(message = "El attemptNo es requerido")
    @Min(value = 1, message = "El attemptNo debe ser mayor o igual a 1")
    private Integer attemptNo;

    //valida proveedor opcional
    @Size(max = 100, message = "El provider soporta hasta 100 caracteres")
    private String provider;

    private String requestMeta;

    private String responseMeta;

    //valida resultado requerido
    @NotNull(message = "El success es requerido")
    private Boolean success;

    private String errorMessage;

}
