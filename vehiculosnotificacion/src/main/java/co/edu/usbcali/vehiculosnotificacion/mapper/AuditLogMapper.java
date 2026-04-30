package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateAuditLogResponse;
import co.edu.usbcali.vehiculosnotificacion.model.AuditLog;
import co.edu.usbcali.vehiculosnotificacion.model.User;

import java.util.List;
import java.util.Objects;

public class AuditLogMapper {

    public static CreateAuditLogResponse entityToCreateAuditLogResponse(AuditLog auditLog) {
        return CreateAuditLogResponse.builder()
                .id(auditLog.getId())
                .userName(Objects.nonNull(auditLog.getUser()) ? auditLog.getUser().getFullName() : null)
                .entityType(auditLog.getEntityType())
                .entityId(auditLog.getEntityId())
                .action(auditLog.getAction())
                .beforeJson(auditLog.getBeforeJson())
                .afterJson(auditLog.getAfterJson())
                .ip(auditLog.getIp())
                .userAgent(auditLog.getUserAgent())
                .createdAt(auditLog.getCreatedAt())
                .build();
    }

    public static AuditLog createAuditLogRequestToEntity(CreateAuditLogRequest createAuditLogRequest, User user) {
        return AuditLog.builder()
                .user(user)
                .entityType(createAuditLogRequest.getEntityType())
                .entityId(createAuditLogRequest.getEntityId())
                .action(createAuditLogRequest.getAction())
                .beforeJson(createAuditLogRequest.getBeforeJson())
                .afterJson(createAuditLogRequest.getAfterJson())
                .ip(createAuditLogRequest.getIp())
                .userAgent(createAuditLogRequest.getUserAgent())
                .build();
    }

    public static List<CreateAuditLogResponse> entityToListCreateAuditLogResponse(List<AuditLog> auditLogs) {
        return auditLogs.stream()
                .map(AuditLogMapper::entityToCreateAuditLogResponse)
                .toList();
    }

}
