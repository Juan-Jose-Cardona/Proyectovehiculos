package co.edu.usbcali.vehiculosnotificacion.service.impl;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationAttemptResponse;
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

    @Override
    public CreateNotificationAttemptResponse createNotificationAttempt(CreateNotificationAttemptRequest request) throws Exception {

        if (request == null) {
            throw new Exception("El objeto CreateNotificationAttemptRequest no puede ser nulo");
        }

        if (request.getNotificationId() == null || request.getNotificationId() <= 0) {
            throw new Exception("El notificationId es requerido");
        }

        if (request.getAttemptNo() == null || request.getAttemptNo() <= 0) {
            throw new Exception("El numero de intento es requerido");
        }

        if (request.getProvider() != null && request.getProvider().length() > 100) {
            throw new Exception("El provider soporta hasta 100 caracteres");
        }

        if (request.getSuccess() == null) {
            throw new Exception("El estado success es requerido");
        }

        Notification notification = notificationRepository.findById(request.getNotificationId())
                .orElseThrow(() -> new Exception("No se encontro la notification con id " + request.getNotificationId()));

        NotificationAttempt notificationAttempt = NotificationAttemptMapper.createNotificationAttemptRequestToEntity(
                request,
                notification
        );

        notificationAttempt = notificationAttemptRepository.save(notificationAttempt);

        return NotificationAttemptMapper.entityToCreateNotificationAttemptResponse(notificationAttempt);
    }

    @Override
    public List<CreateNotificationAttemptResponse> getAllNotificationAttempts() {
        List<NotificationAttempt> attempts = notificationAttemptRepository.findAll();
        return NotificationAttemptMapper.entityToListCreateNotificationAttemptResponse(attempts);
    }

    @Override
    public CreateNotificationAttemptResponse getNotificationAttemptById(Long id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        NotificationAttempt attempt = notificationAttemptRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el notificationAttempt con id " + id));

        return NotificationAttemptMapper.entityToCreateNotificationAttemptResponse(attempt);
    }

    @Override
    public CreateNotificationAttemptResponse updateNotificationAttempt(Long id, UpdateNotificationAttemptRequest request) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        if (request == null) {
            throw new Exception("El objeto UpdateNotificationAttemptRequest no puede ser nulo");
        }

        NotificationAttempt attempt = notificationAttemptRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el notificationAttempt con id " + id));

        if (request.getNotificationId() != null) {
            Notification notification = notificationRepository.findById(request.getNotificationId())
                    .orElseThrow(() -> new Exception("No se encontro la notification con id " + request.getNotificationId()));
            attempt.setNotification(notification);
        }

        if (request.getAttemptNo() != null) {
            if (request.getAttemptNo() <= 0) {
                throw new Exception("El numero de intento debe ser mayor a 0");
            }
            attempt.setAttemptNo(request.getAttemptNo());
        }

        if (request.getProvider() != null) {
            if (request.getProvider().length() > 100) {
                throw new Exception("El provider soporta hasta 100 caracteres");
            }
            attempt.setProvider(request.getProvider());
        }

        if (request.getRequestMeta() != null) attempt.setRequestMeta(request.getRequestMeta());
        if (request.getResponseMeta() != null) attempt.setResponseMeta(request.getResponseMeta());
        if (request.getSuccess() != null) attempt.setSuccess(request.getSuccess());
        if (request.getErrorMessage() != null) attempt.setErrorMessage(request.getErrorMessage());

        attempt = notificationAttemptRepository.save(attempt);

        return NotificationAttemptMapper.entityToCreateNotificationAttemptResponse(attempt);
    }

    @Override
    public void deleteNotificationAttempt(Long id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        NotificationAttempt attempt = notificationAttemptRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el notificationAttempt con id " + id));

        notificationAttemptRepository.delete(attempt);
    }
}