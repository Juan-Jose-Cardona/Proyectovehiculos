package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationAttemptResponse;
import co.edu.usbcali.vehiculosnotificacion.model.Notification;
import co.edu.usbcali.vehiculosnotificacion.model.NotificationAttempt;

import java.util.List;
import java.util.Objects;

public class NotificationAttemptMapper {

    public static CreateNotificationAttemptResponse entityToCreateNotificationAttemptResponse(NotificationAttempt notificationAttempt) {
        return CreateNotificationAttemptResponse.builder()
                .id(notificationAttempt.getId())
                .notificationId(
                        Objects.nonNull(notificationAttempt.getNotification())
                                ? notificationAttempt.getNotification().getId()
                                : null
                )
                .attemptNo(notificationAttempt.getAttemptNo())
                .provider(notificationAttempt.getProvider())
                .requestMeta(notificationAttempt.getRequestMeta())
                .responseMeta(notificationAttempt.getResponseMeta())
                .success(notificationAttempt.getSuccess())
                .errorMessage(notificationAttempt.getErrorMessage())
                .createdAt(notificationAttempt.getCreatedAt())
                .build();
    }

    public static NotificationAttempt createNotificationAttemptRequestToEntity(
            CreateNotificationAttemptRequest createNotificationAttemptRequest,
            Notification notification
    ) {
        return NotificationAttempt.builder()
                .notification(notification)
                .attemptNo(createNotificationAttemptRequest.getAttemptNo())
                .provider(createNotificationAttemptRequest.getProvider())
                .requestMeta(createNotificationAttemptRequest.getRequestMeta())
                .responseMeta(createNotificationAttemptRequest.getResponseMeta())
                .success(createNotificationAttemptRequest.getSuccess())
                .errorMessage(createNotificationAttemptRequest.getErrorMessage())
                .build();
    }

    public static List<CreateNotificationAttemptResponse> entityToListCreateNotificationAttemptResponse(List<NotificationAttempt> notificationAttempts) {
        return notificationAttempts.stream()
                .map(NotificationAttemptMapper::entityToCreateNotificationAttemptResponse)
                .toList();
    }

}
