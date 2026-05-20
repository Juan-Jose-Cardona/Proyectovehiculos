package co.edu.usbcali.vehiculosnotificacion.service.impl;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateNotificationResponse;
import co.edu.usbcali.vehiculosnotificacion.mapper.NotificationMapper;
import co.edu.usbcali.vehiculosnotificacion.model.Notification;
import co.edu.usbcali.vehiculosnotificacion.model.User;
import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;
import co.edu.usbcali.vehiculosnotificacion.model.Obligation;
import co.edu.usbcali.vehiculosnotificacion.repository.NotificationRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.UserRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.VehicleRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.ObligationRepository;
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

    //crea notificacion
    @Override
    public CreateNotificationResponse createNotification(CreateNotificationRequest createNotificationRequest) throws Exception {

        try {

            //valida request no nulo
            if (createNotificationRequest == null) {
                throw new Exception("El objeto CreateNotificationRequest no puede ser nulo");
            }

            //valida user id
            if (createNotificationRequest.getUserId() == null || createNotificationRequest.getUserId() <= 0) {
                throw new Exception("El userId es requerido");
            }

            //valida vehicle id
            if (createNotificationRequest.getVehicleId() == null || createNotificationRequest.getVehicleId() <= 0) {
                throw new Exception("El vehicleId es requerido");
            }

            //valida obligation id
            if (createNotificationRequest.getObligationId() == null || createNotificationRequest.getObligationId() <= 0) {
                throw new Exception("El obligationId es requerido");
            }

            //valida canal requerido
            if (createNotificationRequest.getChannel() == null) {
                throw new Exception("El canal es requerido");
            }

            //valida kind requerido
            if (createNotificationRequest.getKind() == null) {
                throw new Exception("El kind es requerido");
            }

            //valida fecha vencimiento
            if (createNotificationRequest.getDueDate() == null) {
                throw new Exception("La fecha de vencimiento es requerida");
            }

            //valida fecha programada
            if (createNotificationRequest.getScheduledFor() == null) {
                throw new Exception("La fecha programada es requerida");
            }

            //busca usuario por id
            User user = userRepository.findById(createNotificationRequest.getUserId())
                    .orElseThrow(() -> new Exception(
                            "No se encontro el user con id " + createNotificationRequest.getUserId()
                    ));

            //busca vehiculo por id
            Vehicle vehicle = vehicleRepository.findById(createNotificationRequest.getVehicleId())
                    .orElseThrow(() -> new Exception(
                            "No se encontro el vehicle con id " + createNotificationRequest.getVehicleId()
                    ));

            //busca obligacion por id
            Obligation obligation = obligationRepository.findById(createNotificationRequest.getObligationId())
                    .orElseThrow(() -> new Exception(
                            "No se encontro la obligation con id " + createNotificationRequest.getObligationId()
                    ));

            //convierte request a entidad
            Notification notification = NotificationMapper.createNotificationRequestToEntity(
                    createNotificationRequest,
                    user,
                    vehicle,
                    obligation
            );

            //guarda entidad
            notification = notificationRepository.save(notification);

            //retorna response
            return NotificationMapper.entityToCreateNotificationResponse(notification);

        } catch (Exception e) {
            throw e;
        }
    }

    //obtiene lista notificaciones
    @Override
    public List<CreateNotificationResponse> getAllNotifications() {

        List<Notification> notifications = notificationRepository.findAll();
        List<CreateNotificationResponse> createNotificationResponseList = NotificationMapper.entityToListCreateNotificationResponse(notifications);
        return createNotificationResponseList;
    }

    //obtiene notificacion segun id
    @Override
    public CreateNotificationResponse getNotificationById(Integer id) {

        Notification notification = notificationRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Notification not found with id; " + id));

        CreateNotificationResponse createNotificationResponse = NotificationMapper.entityToCreateNotificationResponse(notification);
        return createNotificationResponse;
    }

    //metodo para actualizar atributos
    @Override
    public UpdateNotificationResponse updateNotification(Integer id, UpdateNotificationRequest updateNotificationRequest) throws Exception {

        try {

            // Validar id no nulo
            if (id == null){
                throw new Exception("El objeto Notification debe existir");
            }

            //valida request no nulo
            if (updateNotificationRequest == null) {
                throw new Exception("El objeto UpdateNotificationRequest no puede ser nulo");
            }

            //busca notificacion por id
            Notification notification = notificationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Notification not found with id; " + id));

            //actualiza usuario notificacion
            if (updateNotificationRequest.getUserId() != null) {
                User user = userRepository.findById(updateNotificationRequest.getUserId())
                        .orElseThrow(() -> new Exception(
                                "No se encontro el user con id " + updateNotificationRequest.getUserId()
                        ));

                notification.setUser(user);
            }

            //actualiza vehiculo notificacion
            if (updateNotificationRequest.getVehicleId() != null) {
                Vehicle vehicle = vehicleRepository.findById(updateNotificationRequest.getVehicleId())
                        .orElseThrow(() -> new Exception(
                                "No se encontro el vehicle con id " + updateNotificationRequest.getVehicleId()
                        ));

                notification.setVehicle(vehicle);
            }

            //actualiza obligacion notificacion
            if (updateNotificationRequest.getObligationId() != null) {
                Obligation obligation = obligationRepository.findById(updateNotificationRequest.getObligationId())
                        .orElseThrow(() -> new Exception(
                                "No se encontro la obligation con id " + updateNotificationRequest.getObligationId()
                        ));

                notification.setObligation(obligation);
            }

            //actualiza canal
            if (updateNotificationRequest.getChannel() != null) {
                notification.setChannel(updateNotificationRequest.getChannel());
            }

            //actualiza tipo notificacion
            if (updateNotificationRequest.getKind() != null) {
                notification.setKind(updateNotificationRequest.getKind());
            }

            //actualiza dias previos
            if (updateNotificationRequest.getDaysBeforeDue() != null) {
                notification.setDaysBeforeDue(updateNotificationRequest.getDaysBeforeDue());
            }

            //actualiza fecha vencimiento
            if (updateNotificationRequest.getDueDate() != null) {
                notification.setDueDate(updateNotificationRequest.getDueDate());
            }

            //actualiza fecha programada
            if (updateNotificationRequest.getScheduledFor() != null) {
                notification.setScheduledFor(updateNotificationRequest.getScheduledFor());
            }

            //actualiza payload json
            if (updateNotificationRequest.getPayloadJson() != null) {
                notification.setPayloadJson(updateNotificationRequest.getPayloadJson());
            }

            //actualiza estado
            if (updateNotificationRequest.getStatus() != null) {
                notification.setStatus(updateNotificationRequest.getStatus());
            }

            //actualiza intentos
            if (updateNotificationRequest.getAttemptCount() != null) {
                notification.setAttemptCount(updateNotificationRequest.getAttemptCount());
            }

            //actualiza ultimo error
            if (updateNotificationRequest.getLastError() != null) {
                notification.setLastError(updateNotificationRequest.getLastError());
            }

            //actualiza bloqueado por
            if (updateNotificationRequest.getLockedBy() != null) {
                notification.setLockedBy(updateNotificationRequest.getLockedBy());
            }

            //actualiza fecha bloqueo
            if (updateNotificationRequest.getLockedAt() != null) {
                notification.setLockedAt(updateNotificationRequest.getLockedAt());
            }

            //actualiza fecha envio
            if (updateNotificationRequest.getSentAt() != null) {
                notification.setSentAt(updateNotificationRequest.getSentAt());
            }

            //guarda entidad actualizada
            notification = notificationRepository.save(notification);

            //convierte a response dto
            UpdateNotificationResponse response = NotificationMapper.entityToUpdateNotificationResponse(notification);

            //retorna dto
            return response;

        } catch (Exception e) {
            throw e;
        }
    }

}
