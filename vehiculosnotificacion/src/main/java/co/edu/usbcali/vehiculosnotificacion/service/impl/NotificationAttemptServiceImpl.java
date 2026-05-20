package co.edu.usbcali.vehiculosnotificacion.service.impl;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationAttemptResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateNotificationAttemptResponse;
import co.edu.usbcali.vehiculosnotificacion.mapper.NotificationAttemptMapper;
import co.edu.usbcali.vehiculosnotificacion.model.Notification;
import co.edu.usbcali.vehiculosnotificacion.model.NotificationAttempt;
import co.edu.usbcali.vehiculosnotificacion.repository.NotificationAttemptRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.NotificationRepository;
import co.edu.usbcali.vehiculosnotificacion.service.NotificationAttemptService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationAttemptServiceImpl implements NotificationAttemptService {

    private final NotificationAttemptRepository notificationAttemptRepository;
    private final NotificationRepository notificationRepository;

    //crea notification attempt
    @Override
    public CreateNotificationAttemptResponse createNotificationAttempt(CreateNotificationAttemptRequest createNotificationAttemptRequest) throws Exception {

        try {

            //valida request no nulo
            if (createNotificationAttemptRequest == null) {
                throw new Exception("El objeto CreateNotificationAttemptRequest no puede ser nulo");
            }

            //valida notification id
            if (createNotificationAttemptRequest.getNotificationId() == null
                    || createNotificationAttemptRequest.getNotificationId() <= 0) {
                throw new Exception("El notificationId es requerido");
            }

            //valida numero intento
            if (createNotificationAttemptRequest.getAttemptNo() == null
                    || createNotificationAttemptRequest.getAttemptNo() <= 0) {
                throw new Exception("El numero de intento es requerido");
            }

            //valida longitud provider
            if (createNotificationAttemptRequest.getProvider() != null
                    && createNotificationAttemptRequest.getProvider().length() > 100) {
                throw new Exception("El provider soporta hasta 100 caracteres");
            }

            //valida success requerido
            if (createNotificationAttemptRequest.getSuccess() == null) {
                throw new Exception("El estado success es requerido");
            }

            //busca notificacion por id
            Notification notification = notificationRepository.findById(createNotificationAttemptRequest.getNotificationId())
                    .orElseThrow(() -> new Exception(
                            "No se encontro la notification con id " + createNotificationAttemptRequest.getNotificationId()
                    ));

            //convierte request a entidad
            NotificationAttempt notificationAttempt = NotificationAttemptMapper.createNotificationAttemptRequestToEntity(
                    createNotificationAttemptRequest,
                    notification
            );

            //guarda entidad
            notificationAttempt = notificationAttemptRepository.save(notificationAttempt);

            //retorna response
            return NotificationAttemptMapper.entityToCreateNotificationAttemptResponse(notificationAttempt);

        } catch (Exception e) {
            throw e;
        }
    }


    //obtiene lista notification attempts
    @Override
    public List<CreateNotificationAttemptResponse> getAllNotificationAttempts() {

        List<NotificationAttempt> notificationAttempts = notificationAttemptRepository.findAll();
        List<CreateNotificationAttemptResponse> createNotificationAttemptResponseList = NotificationAttemptMapper.entityToListCreateNotificationAttemptResponse(notificationAttempts);
        return createNotificationAttemptResponseList;
    }

    //obtiene notification attempt segun id
    @Override
    public CreateNotificationAttemptResponse getNotificationAttemptById(Long id) {

        NotificationAttempt notificationAttempt = notificationAttemptRepository.findById(id).
                orElseThrow(() -> new RuntimeException("NotificationAttempt not found with id; " + id));

        CreateNotificationAttemptResponse createNotificationAttemptResponse = NotificationAttemptMapper.entityToCreateNotificationAttemptResponse(notificationAttempt);
        return createNotificationAttemptResponse;
    }



    //metodo para actualizar atributos
    @Override
    public UpdateNotificationAttemptResponse updateNotificationAttempt(Long id, UpdateNotificationAttemptRequest updateNotificationAttemptRequest) throws Exception {

        try {

            // Validar id no nulo
            if (id == null){
                throw new Exception("El objeto Notification Attempt debe existir");
            }

            //valida request no nulo
            if (updateNotificationAttemptRequest == null) {
                throw new Exception("El objeto UpdateNotificationAttemptRequest no puede ser nulo");
            }

            //busca notification attempt por id
            NotificationAttempt notificationAttempt = notificationAttemptRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("NotificationAttempt not found with id; " + id));

            //actualiza notificacion attempt
            if (updateNotificationAttemptRequest.getNotificationId() != null) {
                Notification notification = notificationRepository.findById(updateNotificationAttemptRequest.getNotificationId())
                        .orElseThrow(() -> new Exception(
                                "No se encontro la notification con id " + updateNotificationAttemptRequest.getNotificationId()
                        ));

                notificationAttempt.setNotification(notification);
            }

            //actualiza numero intento
            if (updateNotificationAttemptRequest.getAttemptNo() != null) {
                notificationAttempt.setAttemptNo(updateNotificationAttemptRequest.getAttemptNo());
            }

            //actualiza proveedor
            if (updateNotificationAttemptRequest.getProvider() != null) {
                notificationAttempt.setProvider(updateNotificationAttemptRequest.getProvider());
            }

            //actualiza request meta
            if (updateNotificationAttemptRequest.getRequestMeta() != null) {
                notificationAttempt.setRequestMeta(updateNotificationAttemptRequest.getRequestMeta());
            }

            //actualiza response meta
            if (updateNotificationAttemptRequest.getResponseMeta() != null) {
                notificationAttempt.setResponseMeta(updateNotificationAttemptRequest.getResponseMeta());
            }

            //actualiza success
            if (updateNotificationAttemptRequest.getSuccess() != null) {
                notificationAttempt.setSuccess(updateNotificationAttemptRequest.getSuccess());
            }

            //actualiza mensaje error
            if (updateNotificationAttemptRequest.getErrorMessage() != null) {
                notificationAttempt.setErrorMessage(updateNotificationAttemptRequest.getErrorMessage());
            }

            //guarda entidad actualizada
            notificationAttempt = notificationAttemptRepository.save(notificationAttempt);

            //convierte a response dto
            UpdateNotificationAttemptResponse response = NotificationAttemptMapper.entityToUpdateNotificationAttemptResponse(notificationAttempt);

            //retorna dto
            return response;

        } catch (Exception e) {
            throw e;
        }
    }

}
