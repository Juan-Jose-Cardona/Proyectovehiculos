package co.edu.usbcali.vehiculosnotificacion.dto.response;

//importa la clase auditlog para que se reconozca el atributo AuditAction
import co.edu.usbcali.vehiculosnotificacion.model.enums.AuditAction;


//importa para el atributo timestamp
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UpdateAuditLogResponse {

    private Long id;
    private Integer userId;
    private String userName;
    private String entityType;
    private Integer entityId;
    private AuditAction action;
    private String beforeJson;
    private String afterJson;
    private String ip;
    private String userAgent;
    private Timestamp createdAt;

}
