package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationAttemptResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateNotificationAttemptResponse;
import co.edu.usbcali.vehiculosnotificacion.model.Notification;
import co.edu.usbcali.vehiculosnotificacion.model.NotificationAttempt;

import java.util.List;
import java.util.Objects;

public class NotificationAttemptMapper {

    //convierte entidad a response
    public static CreateNotificationAttemptResponse entityToCreateNotificationAttemptResponse(NotificationAttempt notificationAttempt) {

        //instanciar nuevo objeto response
        CreateNotificationAttemptResponse response = CreateNotificationAttemptResponse.builder()
                .id(notificationAttempt.getId())
                .notificationId(Objects.nonNull(notificationAttempt.getNotification()) ? notificationAttempt.getNotification().getId() : null)
                .attemptNo(notificationAttempt.getAttemptNo())
                .provider(notificationAttempt.getProvider())
                .requestMeta(notificationAttempt.getRequestMeta())
                .responseMeta(notificationAttempt.getResponseMeta())
                .success(notificationAttempt.getSuccess())
                .errorMessage(notificationAttempt.getErrorMessage())
                .createdAt(notificationAttempt.getCreatedAt())
                .build();

        return response;
    }

    //convierte lista entidades a response
    public static List<CreateNotificationAttemptResponse> entityToListCreateNotificationAttemptResponse(List<NotificationAttempt> notificationAttempts) {

        //mapea lista usando stream
        return notificationAttempts.stream().map(NotificationAttemptMapper::entityToCreateNotificationAttemptResponse).toList();
    }

    //convierte request a entidad
    public static NotificationAttempt createNotificationAttemptRequestToEntity(CreateNotificationAttemptRequest createNotificationAttemptRequest, Notification notification
    ) {

        //construye entidad desde request
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


    //convierte entidad a update response
    public static UpdateNotificationAttemptResponse entityToUpdateNotificationAttemptResponse(NotificationAttempt notificationAttempt) {

        //instanciar nuevo objeto response
        UpdateNotificationAttemptResponse response = UpdateNotificationAttemptResponse.builder().id(notificationAttempt.getId()).notificationId(Objects.nonNull(notificationAttempt.getNotification()) ? notificationAttempt.getNotification().getId() : null)
                .attemptNo(notificationAttempt.getAttemptNo())
                .provider(notificationAttempt.getProvider())
                .requestMeta(notificationAttempt.getRequestMeta())
                .responseMeta(notificationAttempt.getResponseMeta())
                .success(notificationAttempt.getSuccess())
                .errorMessage(notificationAttempt.getErrorMessage())
                .createdAt(notificationAttempt.getCreatedAt())
                .build();

        //retorna response
        return response;
    }


}
