package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationAttemptResponse;

import java.util.List;

public interface NotificationAttemptService {

    CreateNotificationAttemptResponse createNotificationAttempt(CreateNotificationAttemptRequest createNotificationAttemptRequest) throws Exception;

    List<CreateNotificationAttemptResponse> getAllNotificationAttempts();

    CreateNotificationAttemptResponse getNotificationAttemptById(Long id) throws Exception;

    CreateNotificationAttemptResponse updateNotificationAttempt(Long id, UpdateNotificationAttemptRequest updateNotificationAttemptRequest) throws Exception;

    void deleteNotificationAttempt(Long id) throws Exception;

}
