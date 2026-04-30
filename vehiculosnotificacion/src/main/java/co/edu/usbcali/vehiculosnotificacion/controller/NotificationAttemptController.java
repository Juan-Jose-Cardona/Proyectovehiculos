package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationAttemptResponse;
import co.edu.usbcali.vehiculosnotificacion.service.NotificationAttemptService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notification-attempts")
public class NotificationAttemptController {

    private final NotificationAttemptService notificationAttemptService;

    @GetMapping("/all")
    public List<CreateNotificationAttemptResponse> getAllNotificationAttempts() {
        return notificationAttemptService.getAllNotificationAttempts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateNotificationAttemptResponse> getNotificationAttemptById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(notificationAttemptService.getNotificationAttemptById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateNotificationAttemptResponse> createNotificationAttempt(
            @RequestBody CreateNotificationAttemptRequest request
    ) throws Exception {
        return new ResponseEntity<>(notificationAttemptService.createNotificationAttempt(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateNotificationAttemptResponse> updateNotificationAttempt(
            @PathVariable Long id,
            @RequestBody UpdateNotificationAttemptRequest request
    ) throws Exception {
        return new ResponseEntity<>(notificationAttemptService.updateNotificationAttempt(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotificationAttempt(@PathVariable Long id) throws Exception {
        notificationAttemptService.deleteNotificationAttempt(id);
        return new ResponseEntity<>("NotificationAttempt eliminado correctamente", HttpStatus.OK);
    }
}