package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationResponse;

import java.util.List;

public interface NotificationService {

    CreateNotificationResponse createNotification(CreateNotificationRequest createNotificationRequest) throws Exception;

    List<CreateNotificationResponse> getAllNotifications();

    CreateNotificationResponse getNotificationById(Integer id) throws Exception;

    CreateNotificationResponse updateNotification(Integer id, UpdateNotificationRequest updateNotificationRequest) throws Exception;

    void deleteNotification(Integer id) throws Exception;

}
