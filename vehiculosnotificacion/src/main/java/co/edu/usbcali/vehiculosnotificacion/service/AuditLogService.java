package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateAuditLogResponse;

import java.util.List;

public interface AuditLogService {

    CreateAuditLogResponse createAuditLog(CreateAuditLogRequest createAuditLogRequest) throws Exception;

    List<CreateAuditLogResponse> getAllAuditLogs();

    CreateAuditLogResponse getAuditLogById(Long id) throws Exception;

    CreateAuditLogResponse updateAuditLog(Long id, UpdateAuditLogRequest updateAuditLogRequest) throws Exception;

    void deleteAuditLog(Long id) throws Exception;

}
