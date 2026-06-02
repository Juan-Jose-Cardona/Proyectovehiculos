package co.edu.usbcali.vehiculosnotificacion.service.impl;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateAuditLogResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateAuditLogResponse;
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

    //crea auditlog
    @Override
    public CreateAuditLogResponse createAuditLog(CreateAuditLogRequest createAuditLogRequest) throws Exception {

        try {

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
                    .orElseThrow(() -> new Exception(
                            "No se encontro el user con id " + createAuditLogRequest.getUserId()
                    ));

            AuditLog auditLog = AuditLogMapper.createAuditLogRequestToEntity(createAuditLogRequest, user);

            auditLog = auditLogRepository.save(auditLog);

            return AuditLogMapper.entityToCreateAuditLogResponse(auditLog);

        } catch (Exception e) {
            throw e;
        }
    }


    //consigue la lista
    @Override
    public List<CreateAuditLogResponse> getAllAuditLogs() {

        List<AuditLog> auditLogs = auditLogRepository.findAll();
        List<CreateAuditLogResponse> createAuditLogResponseList = AuditLogMapper.entityToListCreateAuditLogResponse(auditLogs);
        return createAuditLogResponseList;

    }

    //consigue segun id
    @Override
    public CreateAuditLogResponse getAuditLogById(Long id) {

        AuditLog auditLog = auditLogRepository.findById(id).
                orElseThrow(() -> new RuntimeException("El ID:  " + id + ". No es valido"));
        CreateAuditLogResponse createAuditLogResponse = AuditLogMapper.entityToCreateAuditLogResponse(auditLog);
        return createAuditLogResponse;
    }

    //actualiza atributos
    @Override
    public UpdateAuditLogResponse updateAuditLog(Long id, UpdateAuditLogRequest updateAuditLogRequest) throws Exception {

        try {

            // Validar id no nulo
            if (id == null){
                throw new Exception("El objeto AuditLog debe existir");
            }

            //valida request no nulo
            if (updateAuditLogRequest == null) {
                throw new Exception("El objeto UpdateAuditLogRequest no puede ser nulo");
            }

            //busca audit log por id
            AuditLog auditLog = auditLogRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));

            //actualiza usuario audit log
            if (updateAuditLogRequest.getUserId() != null) {
                User user = userRepository.findById(updateAuditLogRequest.getUserId())
                        .orElseThrow(() -> new Exception(
                                "No se encontro el user con id " + updateAuditLogRequest.getUserId()
                        ));

                auditLog.setUser(user);
            }

            //actualiza campos entidad
            if (updateAuditLogRequest.getEntityType() != null) {
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

            //guarda entidad actualizada
            auditLog = auditLogRepository.save(auditLog);

            //convierte a response
            UpdateAuditLogResponse response = AuditLogMapper.entityToUpdateAuditLogResponse(auditLog);

            //retorna
            return response;

        } catch (Exception e) {
            throw e;
        }
    }

    //metodo para eliminar usuario
    @Override
    public void deleteAuditLog(Integer id) throws Exception {

        try {

            //valida id no nulo
            if (id == null){
                throw new Exception("El id del usuario es requerido");
            }

            //busca usuario por id
            AuditLog auditLog = auditLogRepository.findById(Long.valueOf(id)).
                    orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));

            //elimina usuario
            auditLogRepository.delete(auditLog);

        } catch (Exception e) {
            throw e;
        }
    }


}
