package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationResponse;
import co.edu.usbcali.vehiculosnotificacion.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/all")
    public List<CreateNotificationResponse> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateNotificationResponse> getNotificationById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(notificationService.getNotificationById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateNotificationResponse> createNotification(@RequestBody CreateNotificationRequest request) throws Exception {
        return new ResponseEntity<>(notificationService.createNotification(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateNotificationResponse> updateNotification(
            @PathVariable Integer id,
            @RequestBody UpdateNotificationRequest request
    ) throws Exception {
        return new ResponseEntity<>(notificationService.updateNotification(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Integer id) throws Exception {
        notificationService.deleteNotification(id);
        return new ResponseEntity<>("Notification eliminada correctamente", HttpStatus.OK);
    }
}