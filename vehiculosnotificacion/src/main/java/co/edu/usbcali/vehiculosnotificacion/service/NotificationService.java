package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateNotificationResponse;

import java.util.List;

public interface NotificationService {

    CreateNotificationResponse createNotification(CreateNotificationRequest createNotificationRequest) throws Exception;

    //get list
    List<CreateNotificationResponse> getAllNotifications();

    //get by id
    CreateNotificationResponse getNotificationById(Integer id);

    //put
    UpdateNotificationResponse updateNotification(Integer id, UpdateNotificationRequest updateNotificationRequest) throws Exception;


}
