package co.edu.usbcali.vehiculosnotificacion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNotificationAttemptRequest {

    private Integer notificationId;
    private Integer attemptNo;
    private String provider;
    private String requestMeta;
    private String responseMeta;
    private Boolean success;
    private String errorMessage;

}