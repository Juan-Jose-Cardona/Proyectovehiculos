package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationResponse;

//importacion por que necesita llave foranea
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateNotificationResponse;
import co.edu.usbcali.vehiculosnotificacion.model.Notification;
import co.edu.usbcali.vehiculosnotificacion.model.User;
import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;
import co.edu.usbcali.vehiculosnotificacion.model.Obligation;

import java.util.List;
import java.util.Objects;

public class NotificationMapper {

    //convierte entidad a response
    public static CreateNotificationResponse entityToCreateNotificationResponse(Notification notification){

        //instanciar nuevo objeto response
        CreateNotificationResponse response = CreateNotificationResponse.builder()
                .id(notification.getId())
                .userId(Objects.nonNull(notification.getUser()) ? notification.getUser().getId() : null)
                .vehicleId(Objects.nonNull(notification.getVehicle()) ? notification.getVehicle().getId() : null)
                .obligationId(Objects.nonNull(notification.getObligation()) ? notification.getObligation().getId() : null)
                .channel(notification.getChannel())
                .kind(notification.getKind())
                .daysBeforeDue(notification.getDaysBeforeDue())
                .dueDate(notification.getDueDate())
                .scheduledFor(notification.getScheduledFor())
                .payloadJson(notification.getPayloadJson())
                .status(notification.getStatus())
                .attemptCount(notification.getAttemptCount())
                .lastError(notification.getLastError())
                .lockedBy(notification.getLockedBy())
                .lockedAt(notification.getLockedAt())
                .sentAt(notification.getSentAt())
                .createdAt(notification.getCreatedAt())
                .updatedAt(notification.getUpdatedAt())
                .build();

        return response;
    }


    //convierte lista entidades a response
    public static List<CreateNotificationResponse> entityToListCreateNotificationResponse(List<Notification> notifications){

        //mapea lista usando stream
        return notifications.stream().map(NotificationMapper::entityToCreateNotificationResponse).toList();
    }

    //convierte request a entidad
    public static Notification createNotificationRequestToEntity(CreateNotificationRequest request, User user, Vehicle vehicle, Obligation obligation){

        //construye entidad desde request
        return Notification.builder()
                .user(user)
                .vehicle(vehicle)
                .obligation(obligation)
                .channel(request.getChannel())
                .kind(request.getKind())
                .daysBeforeDue(request.getDaysBeforeDue())
                .dueDate(request.getDueDate())
                .scheduledFor(request.getScheduledFor())
                .payloadJson(request.getPayloadJson())
                .status(request.getStatus())
                .attemptCount(request.getAttemptCount())
                .lastError(request.getLastError())
                .lockedBy(request.getLockedBy())
                .lockedAt(request.getLockedAt())
                .sentAt(request.getSentAt())
                .build();
    }

    //convierte entidad a update response
    public static UpdateNotificationResponse entityToUpdateNotificationResponse(Notification notification){

        //instanciar nuevo objeto response
        UpdateNotificationResponse response = UpdateNotificationResponse.builder()
                .id(notification.getId())
                .userId(Objects.nonNull(notification.getUser()) ? notification.getUser().getId() : null)
                .vehicleId(Objects.nonNull(notification.getVehicle()) ? notification.getVehicle().getId() : null)
                .obligationId(Objects.nonNull(notification.getObligation()) ? notification.getObligation().getId() : null)
                .channel(notification.getChannel())
                .kind(notification.getKind())
                .daysBeforeDue(notification.getDaysBeforeDue())
                .dueDate(notification.getDueDate())
                .scheduledFor(notification.getScheduledFor())
                .payloadJson(notification.getPayloadJson())
                .status(notification.getStatus())
                .attemptCount(notification.getAttemptCount())
                .lastError(notification.getLastError())
                .lockedBy(notification.getLockedBy())
                .lockedAt(notification.getLockedAt())
                .sentAt(notification.getSentAt())
                .createdAt(notification.getCreatedAt())
                .updatedAt(notification.getUpdatedAt())
                .build();

        //retorna response
        return response;
    }

}
