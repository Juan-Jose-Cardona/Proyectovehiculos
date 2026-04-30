package co.edu.usbcali.vehiculosnotificacion.service.impl;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateAuditLogResponse;
import co.edu.usbcali.vehiculosnotificacion.mapper.AuditLogMapper;
import co.edu.usbcali.vehiculosnotificacion.model.AuditLog;
import co.edu.usbcali.vehiculosnotificacion.model.User;
import co.edu.usbcali.vehiculosnotificacion.repository.AuditLogRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.UserRepository;
import co.edu.usbcali.vehiculosnotificacion.service.AuditLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final UserRepository userRepository;

    @Override
    public CreateAuditLogResponse createAuditLog(CreateAuditLogRequest createAuditLogRequest) throws Exception {

        if (createAuditLogRequest == null) {
            throw new Exception("El objeto CreateAuditLogRequest no puede ser nulo");
        }

        if (createAuditLogRequest.getUserId() == null || createAuditLogRequest.getUserId() <= 0) {
            throw new Exception("El userId es requerido");
        }

        if (createAuditLogRequest.getEntityType() == null || createAuditLogRequest.getEntityType().isBlank()) {
            throw new Exception("El tipo de entidad es requerido");
        }

        if (createAuditLogRequest.getEntityType().length() > 100) {
            throw new Exception("El tipo de entidad soporta hasta 100 caracteres");
        }

        if (createAuditLogRequest.getAction() == null) {
            throw new Exception("La accion es requerida");
        }

        User user = userRepository.findById(createAuditLogRequest.getUserId())
                .orElseThrow(() -> new Exception("No se encontro el user con id " + createAuditLogRequest.getUserId()));

        AuditLog auditLog = AuditLogMapper.createAuditLogRequestToEntity(createAuditLogRequest, user);

        auditLog = auditLogRepository.save(auditLog);

        return AuditLogMapper.entityToCreateAuditLogResponse(auditLog);
    }

    @Override
    public List<CreateAuditLogResponse> getAllAuditLogs() {
        List<AuditLog> auditLogs = auditLogRepository.findAll();
        return AuditLogMapper.entityToListCreateAuditLogResponse(auditLogs);
    }

    @Override
    public CreateAuditLogResponse getAuditLogById(Long id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el auditLog con id " + id));

        return AuditLogMapper.entityToCreateAuditLogResponse(auditLog);
    }

    @Override
    public CreateAuditLogResponse updateAuditLog(Long id, UpdateAuditLogRequest updateAuditLogRequest) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        if (updateAuditLogRequest == null) {
            throw new Exception("El objeto UpdateAuditLogRequest no puede ser nulo");
        }

        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el auditLog con id " + id));

        if (updateAuditLogRequest.getUserId() != null) {
            if (updateAuditLogRequest.getUserId() <= 0) {
                throw new Exception("El userId debe ser mayor a 0");
            }

            User user = userRepository.findById(updateAuditLogRequest.getUserId())
                    .orElseThrow(() -> new Exception("No se encontro el user con id " + updateAuditLogRequest.getUserId()));

            auditLog.setUser(user);
        }

        if (updateAuditLogRequest.getEntityType() != null) {
            if (updateAuditLogRequest.getEntityType().isBlank()) {
                throw new Exception("El tipo de entidad no puede estar vacio");
            }

            if (updateAuditLogRequest.getEntityType().length() > 100) {
                throw new Exception("El tipo de entidad soporta hasta 100 caracteres");
            }

            auditLog.setEntityType(updateAuditLogRequest.getEntityType());
        }

        if (updateAuditLogRequest.getEntityId() != null) {
            auditLog.setEntityId(updateAuditLogRequest.getEntityId());
        }

        if (updateAuditLogRequest.getAction() != null) {
            auditLog.setAction(updateAuditLogRequest.getAction());
        }

        if (updateAuditLogRequest.getBeforeJson() != null) {
            auditLog.setBeforeJson(updateAuditLogRequest.getBeforeJson());
        }

        if (updateAuditLogRequest.getAfterJson() != null) {
            auditLog.setAfterJson(updateAuditLogRequest.getAfterJson());
        }

        if (updateAuditLogRequest.getIp() != null) {
            auditLog.setIp(updateAuditLogRequest.getIp());
        }

        if (updateAuditLogRequest.getUserAgent() != null) {
            auditLog.setUserAgent(updateAuditLogRequest.getUserAgent());
        }

        auditLog = auditLogRepository.save(auditLog);

        return AuditLogMapper.entityToCreateAuditLogResponse(auditLog);
    }

    @Override
    public void deleteAuditLog(Long id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el auditLog con id " + id));

        auditLogRepository.delete(auditLog);
    }
}