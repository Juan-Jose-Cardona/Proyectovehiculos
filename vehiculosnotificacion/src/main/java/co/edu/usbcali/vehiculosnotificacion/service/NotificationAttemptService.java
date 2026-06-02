package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationAttemptResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateNotificationAttemptResponse;

import java.util.List;


public interface NotificationAttemptService {

    CreateNotificationAttemptResponse createNotificationAttempt(CreateNotificationAttemptRequest createNotificationAttemptRequest) throws Exception;

    //get all
    List<CreateNotificationAttemptResponse> getAllNotificationAttempts();

    //get by id
    CreateNotificationAttemptResponse getNotificationAttemptById(Long id);

    //update
    UpdateNotificationAttemptResponse updateNotificationAttempt(Long id, UpdateNotificationAttemptRequest updateNotificationAttemptRequest) throws Exception;

    //delete
    void deleteNotificationAttempt(Integer id) throws Exception;


}
