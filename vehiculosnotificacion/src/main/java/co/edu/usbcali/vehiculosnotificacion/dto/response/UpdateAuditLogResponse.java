package co.edu.usbcali.vehiculosnotificacion.dto.response;

import co.edu.usbcali.vehiculosnotificacion.model.enums.AuditAction;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class UpdateAuditLogResponse {

    private Long id;
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
