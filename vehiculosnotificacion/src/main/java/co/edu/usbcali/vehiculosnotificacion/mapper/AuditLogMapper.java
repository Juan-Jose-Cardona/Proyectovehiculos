package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateAuditLogResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateAuditLogResponse;
import co.edu.usbcali.vehiculosnotificacion.model.AuditLog;
import co.edu.usbcali.vehiculosnotificacion.model.User;

import java.util.Objects;
import java.util.List;

public class AuditLogMapper {

    //asigna valores
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

    //convierte lista
    public static List<CreateAuditLogResponse> entityToListCreateAuditLogResponse(List<AuditLog> auditLogs) {
        return auditLogs.stream().map(AuditLogMapper::entityToCreateAuditLogResponse).toList();
    }

    //convierte request a entidad
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


    //convierte entidad a update response
    public static UpdateAuditLogResponse entityToUpdateAuditLogResponse(AuditLog auditLog) {

        //instanciar nuevo objeto
        UpdateAuditLogResponse response = UpdateAuditLogResponse.builder()
                .id(auditLog.getId())
                .userId(Objects.nonNull(auditLog.getUser()) ? auditLog.getUser().getId() : null)
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

        //retorna
        return response;
    }


}
