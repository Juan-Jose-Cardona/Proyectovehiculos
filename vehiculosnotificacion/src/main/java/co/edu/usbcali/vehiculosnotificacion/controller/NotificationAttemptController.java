package co.edu.usbcali.vehiculosnotificacion.controller;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateNotificationAttemptRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateNotificationAttemptResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateNotificationAttemptResponse;
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

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    //lista
    @GetMapping("/all")
    public List<CreateNotificationAttemptResponse> getAllNotificationAttempts(){

        return notificationAttemptService.getAllNotificationAttempts();

    }

    //obtiene segun id
    @GetMapping("/{id}")
    public ResponseEntity<CreateNotificationAttemptResponse> getNotificationAttemptById(@PathVariable Long id){

        CreateNotificationAttemptResponse notificationAttemptResponse = notificationAttemptService.getNotificationAttemptById(id);

        return new ResponseEntity<>(
                notificationAttemptResponse,
                HttpStatus.CREATED
        );

    }

    //crea
    @PostMapping("/create")
    public ResponseEntity<CreateNotificationAttemptResponse> createNotificationAttempt(@RequestBody CreateNotificationAttemptRequest createNotificationAttemptRequest) throws Exception {

        CreateNotificationAttemptResponse notificationAttemptCreated = notificationAttemptService.createNotificationAttempt(createNotificationAttemptRequest);

        return new ResponseEntity<>(
                notificationAttemptCreated,
                HttpStatus.CREATED
        );
    }

    //actualiza
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateNotificationAttemptResponse> updateNotificationAttempt(@PathVariable Long id, @RequestBody UpdateNotificationAttemptRequest updateNotificationAttemptRequest) throws Exception {

        //llama update en service
        UpdateNotificationAttemptResponse notificationAttemptUpdated =
                notificationAttemptService.updateNotificationAttempt(id, updateNotificationAttemptRequest);

        //retorna response
        return new ResponseEntity<>(
                notificationAttemptUpdated,
                HttpStatus.CREATED
        );
    }

}
