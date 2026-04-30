package co.edu.usbcali.vehiculosnotificacion.dto.response;


import lombok.Builder;
import lombok.Getter;
import java.sql.Timestamp;

@Getter
@Builder
public class CreateNotificationAttemptResponse {

    private Long id;
    private Integer notificationId;
    private Integer attemptNo;
    private String provider;
    private String requestMeta;
    private String responseMeta;
    private Boolean success;
    private String errorMessage;
    private Timestamp createdAt;

}
