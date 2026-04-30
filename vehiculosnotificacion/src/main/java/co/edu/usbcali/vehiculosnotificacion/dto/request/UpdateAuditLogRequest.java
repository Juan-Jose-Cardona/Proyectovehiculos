package co.edu.usbcali.vehiculosnotificacion.dto.request;

import co.edu.usbcali.vehiculosnotificacion.model.enums.AuditAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAuditLogRequest {

    private Integer userId;
    private String entityType;
    private Integer entityId;
    private AuditAction action;
    private String beforeJson;
    private String afterJson;
    private String ip;
    private String userAgent;

}