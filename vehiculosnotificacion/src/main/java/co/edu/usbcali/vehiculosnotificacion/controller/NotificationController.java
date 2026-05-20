package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateNotificationResponse;
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

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    //lista
    @GetMapping("/all")
    public List<CreateNotificationResponse> getAllNotifications(){

        return notificationService.getAllNotifications();

    }

    //obtiene uno por id
    @GetMapping("/{id}")
    public ResponseEntity<CreateNotificationResponse> getNotificationById(@PathVariable Integer id){

        CreateNotificationResponse notificationResponse = notificationService.getNotificationById(id);

        return new ResponseEntity<>(
                notificationResponse,
                HttpStatus.CREATED
        );

    }

    //crea
    @PostMapping("/create")
    public ResponseEntity<CreateNotificationResponse> createNotification(@RequestBody CreateNotificationRequest createNotificationRequest) throws Exception {

        CreateNotificationResponse notificationCreated = notificationService.createNotification(createNotificationRequest);

        return new ResponseEntity<>(
                notificationCreated,
                HttpStatus.CREATED
        );
    }

    //actualiza por id
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateNotificationResponse> updateNotification(@PathVariable Integer id, @RequestBody UpdateNotificationRequest updateNotificationRequest) throws Exception {

        //llama update en service
        UpdateNotificationResponse notificationUpdated = notificationService.updateNotification(id, updateNotificationRequest);

        //retorna response
        return new ResponseEntity<>(
                notificationUpdated,
                HttpStatus.CREATED
        );
    }

}
