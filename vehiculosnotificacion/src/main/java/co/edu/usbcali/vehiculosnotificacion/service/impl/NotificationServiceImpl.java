package co.edu.usbcali.vehiculosnotificacion.service.impl;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationResponse;
import co.edu.usbcali.vehiculosnotificacion.mapper.NotificationMapper;
import co.edu.usbcali.vehiculosnotificacion.model.Notification;
import co.edu.usbcali.vehiculosnotificacion.model.Obligation;
import co.edu.usbcali.vehiculosnotificacion.model.User;
import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;
import co.edu.usbcali.vehiculosnotificacion.repository.NotificationRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.ObligationRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.UserRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.VehicleRepository;
import co.edu.usbcali.vehiculosnotificacion.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final ObligationRepository obligationRepository;

    @Override
    public CreateNotificationResponse createNotification(CreateNotificationRequest createNotificationRequest) throws Exception {

        if (createNotificationRequest == null) {
            throw new Exception("El objeto CreateNotificationRequest no puede ser nulo");
        }

        if (createNotificationRequest.getUserId() == null || createNotificationRequest.getUserId() <= 0) {
            throw new Exception("El userId es requerido");
        }

        if (createNotificationRequest.getVehicleId() == null || createNotificationRequest.getVehicleId() <= 0) {
            throw new Exception("El vehicleId es requerido");
        }

        if (createNotificationRequest.getObligationId() == null || createNotificationRequest.getObligationId() <= 0) {
            throw new Exception("El obligationId es requerido");
        }

        if (createNotificationRequest.getChannel() == null) {
            throw new Exception("El canal es requerido");
        }

        if (createNotificationRequest.getDueDate() == null) {
            throw new Exception("La fecha de vencimiento es requerida");
        }

        if (createNotificationRequest.getScheduledFor() == null) {
            throw new Exception("La fecha programada es requerida");
        }

        User user = userRepository.findById(createNotificationRequest.getUserId())
                .orElseThrow(() -> new Exception("No se encontro el user con id " + createNotificationRequest.getUserId()));

        Vehicle vehicle = vehicleRepository.findById(createNotificationRequest.getVehicleId())
                .orElseThrow(() -> new Exception("No se encontro el vehicle con id " + createNotificationRequest.getVehicleId()));

        Obligation obligation = obligationRepository.findById(createNotificationRequest.getObligationId())
                .orElseThrow(() -> new Exception("No se encontro la obligation con id " + createNotificationRequest.getObligationId()));

        Notification notification = NotificationMapper.createNotificationRequestToEntity(
                createNotificationRequest,
                user,
                vehicle,
                obligation
        );

        notification = notificationRepository.save(notification);

        return NotificationMapper.entityToCreateNotificationResponse(notification);
    }

    @Override
    public List<CreateNotificationResponse> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return NotificationMapper.entityToListCreateNotificationResponse(notifications);
    }

    @Override
    public CreateNotificationResponse getNotificationById(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro la notification con id " + id));

        return NotificationMapper.entityToCreateNotificationResponse(notification);
    }

    @Override
    public CreateNotificationResponse updateNotification(Integer id, UpdateNotificationRequest request) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        if (request == null) {
            throw new Exception("El objeto UpdateNotificationRequest no puede ser nulo");
        }

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro la notification con id " + id));

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new Exception("No se encontro el user con id " + request.getUserId()));
            notification.setUser(user);
        }

        if (request.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                    .orElseThrow(() -> new Exception("No se encontro el vehicle con id " + request.getVehicleId()));
            notification.setVehicle(vehicle);
        }

        if (request.getObligationId() != null) {
            Obligation obligation = obligationRepository.findById(request.getObligationId())
                    .orElseThrow(() -> new Exception("No se encontro la obligation con id " + request.getObligationId()));
            notification.setObligation(obligation);
        }

        if (request.getChannel() != null) notification.setChannel(request.getChannel());
        if (request.getKind() != null) notification.setKind(request.getKind());
        if (request.getDaysBeforeDue() != null) notification.setDaysBeforeDue(request.getDaysBeforeDue());
        if (request.getDueDate() != null) notification.setDueDate(request.getDueDate());
        if (request.getScheduledFor() != null) notification.setScheduledFor(request.getScheduledFor());
        if (request.getPayloadJson() != null) notification.setPayloadJson(request.getPayloadJson());
        if (request.getStatus() != null) notification.setStatus(request.getStatus());
        if (request.getAttemptCount() != null) notification.setAttemptCount(request.getAttemptCount());
        if (request.getLastError() != null) notification.setLastError(request.getLastError());
        if (request.getLockedBy() != null) notification.setLockedBy(request.getLockedBy());
        if (request.getLockedAt() != null) notification.setLockedAt(request.getLockedAt());
        if (request.getSentAt() != null) notification.setSentAt(request.getSentAt());

        notification = notificationRepository.save(notification);

        return NotificationMapper.entityToCreateNotificationResponse(notification);
    }

    @Override
    public void deleteNotification(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro la notification con id " + id));

        notificationRepository.delete(notification);
    }
}