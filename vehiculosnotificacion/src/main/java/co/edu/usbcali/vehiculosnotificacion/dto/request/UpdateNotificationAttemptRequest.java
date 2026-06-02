package co.edu.usbcali.vehiculosnotificacion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//valid
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNotificationAttemptRequest {

    //atributos a cambiar
    //valida notificacion positiva
    @Positive(message = "El notificationId debe ser mayor a 0")
    private Integer notificationId;

    //valida intento minimo
    @Min(value = 1, message = "El attemptNo debe ser mayor o igual a 1")
    private Integer attemptNo;

    //valida proveedor tamaño
    @Size(max = 100, message = "El provider soporta hasta 100 caracteres")
    private String provider;

    private String requestMeta;

    private String responseMeta;

    private Boolean success;

    private String errorMessage;

}
