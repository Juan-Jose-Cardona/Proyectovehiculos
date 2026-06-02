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

//importa valid
import jakarta.validation.Valid;

//importa para agregar documentacion de swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@AllArgsConstructor
@RequestMapping("/notification-attempts")
@Tag(name = "notification-attempts", description = "operaciones de intentos de notificacion")
public class NotificationAttemptController {

    private final NotificationAttemptService notificationAttemptService;

    @GetMapping("/ping")
    @Operation(summary = "verificar intentos")
    public String ping() {
        return "pong";
    }

    //lista
    @GetMapping("/all")
    @Operation(summary = "listar intentos")
    public List<CreateNotificationAttemptResponse> getAllNotificationAttempts(){

        return notificationAttemptService.getAllNotificationAttempts();

    }

    //obtiene segun id
    @GetMapping("/{id}")
    @Operation(summary = "buscar intento por id")
    public ResponseEntity<CreateNotificationAttemptResponse> getNotificationAttemptById(@PathVariable Long id){

        CreateNotificationAttemptResponse notificationAttemptResponse = notificationAttemptService.getNotificationAttemptById(id);

        return new ResponseEntity<>(
                notificationAttemptResponse,
                HttpStatus.CREATED
        );

    }

    //crea
    @PostMapping("/create")
    @Operation(summary = "crear intento")
    public ResponseEntity<CreateNotificationAttemptResponse> createNotificationAttempt(
            @Valid @RequestBody CreateNotificationAttemptRequest createNotificationAttemptRequest
    ) throws Exception {

        CreateNotificationAttemptResponse notificationAttemptCreated = notificationAttemptService.createNotificationAttempt(createNotificationAttemptRequest);

        return new ResponseEntity<>(
                notificationAttemptCreated,
                HttpStatus.CREATED
        );
    }

    //actualiza
    @PutMapping("/update/{id}")
    @Operation(summary = "actualizar intento")
    public ResponseEntity<UpdateNotificationAttemptResponse> updateNotificationAttempt(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNotificationAttemptRequest updateNotificationAttemptRequest
    ) throws Exception {

        //llama update en service
        UpdateNotificationAttemptResponse notificationAttemptUpdated =
                notificationAttemptService.updateNotificationAttempt(id, updateNotificationAttemptRequest);

        //retorna response
        return new ResponseEntity<>(
                notificationAttemptUpdated,
                HttpStatus.CREATED
        );
    }

    //elimina notificationAttempt por id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "eliminar notificationAttempt")
    public ResponseEntity<String> deleteNotificationAttempt(@PathVariable Integer id) throws Exception {

        //llama service delete
        notificationAttemptService.deleteNotificationAttempt(id);

        //retorna mensaje
        return new ResponseEntity<>(
                "NotificationAttempt eliminado correctamente",
                HttpStatus.OK
        );
    }


}
