package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateAuditLogResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateAuditLogResponse;

import java.util.List;

public interface AuditLogService {

    //post
    CreateAuditLogResponse createAuditLog(CreateAuditLogRequest createAuditLogRequest) throws Exception;

    //get por lista
    List<CreateAuditLogResponse> getAllAuditLogs();

    //get by id
    CreateAuditLogResponse getAuditLogById(Long id);

    //put
    UpdateAuditLogResponse updateAuditLog(Long id, UpdateAuditLogRequest updateAuditLogRequest) throws Exception;

}
