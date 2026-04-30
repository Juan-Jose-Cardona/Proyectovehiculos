package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationResponse;
import co.edu.usbcali.vehiculosnotificacion.model.Notification;
import co.edu.usbcali.vehiculosnotificacion.model.Obligation;
import co.edu.usbcali.vehiculosnotificacion.model.User;
import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;

import java.util.List;
import java.util.Objects;

public class NotificationMapper {

    public static CreateNotificationResponse entityToCreateNotificationResponse(Notification notification) {
        return CreateNotificationResponse.builder()
                .id(notification.getId())
                .userName(Objects.nonNull(notification.getUser()) ? notification.getUser().getFullName() : null)
                .vehiclePlate(Objects.nonNull(notification.getVehicle()) ? notification.getVehicle().getPlate() : null)
                .obligationType(
                        Objects.nonNull(notification.getObligation()) && Objects.nonNull(notification.getObligation().getType())
                                ? notification.getObligation().getType().name()
                                : null
                )
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
    }

    public static Notification createNotificationRequestToEntity(
            CreateNotificationRequest createNotificationRequest,
            User user,
            Vehicle vehicle,
            Obligation obligation
    ) {
        return Notification.builder()
                .user(user)
                .vehicle(vehicle)
                .obligation(obligation)
                .channel(createNotificationRequest.getChannel())
                .kind(createNotificationRequest.getKind())
                .daysBeforeDue(createNotificationRequest.getDaysBeforeDue())
                .dueDate(createNotificationRequest.getDueDate())
                .scheduledFor(createNotificationRequest.getScheduledFor())
                .payloadJson(createNotificationRequest.getPayloadJson())
                .status(createNotificationRequest.getStatus())
                .attemptCount(createNotificationRequest.getAttemptCount())
                .lastError(createNotificationRequest.getLastError())
                .lockedBy(createNotificationRequest.getLockedBy())
                .lockedAt(createNotificationRequest.getLockedAt())
                .sentAt(createNotificationRequest.getSentAt())
                .build();
    }

    public static List<CreateNotificationResponse> entityToListCreateNotificationResponse(List<Notification> notifications) {
        return notifications.stream()
                .map(NotificationMapper::entityToCreateNotificationResponse)
                .toList();
    }

}
