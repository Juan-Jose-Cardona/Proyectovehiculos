package co.edu.usbcali.vehiculosnotificacion.dto.response;



import lombok.Builder;
import lombok.Getter;


//importa la dependencia para Timestamp
import java.sql.Timestamp;

@Getter
@Builder
public class UpdateNotificationAttemptResponse {

    //atributos que se modifican por el put

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
