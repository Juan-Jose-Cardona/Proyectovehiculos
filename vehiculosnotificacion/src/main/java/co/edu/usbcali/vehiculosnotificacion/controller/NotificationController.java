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


//importa valid
import jakarta.validation.Valid;

//importa para agregar documentacion de swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
@Tag(name = "notifications", description = "operaciones de notificaciones")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/ping")
    @Operation(summary = "verificar notificaciones")
    public String ping() {
        return "pong";
    }

    //lista
    @GetMapping("/all")
    @Operation(summary = "listar notificaciones")
    public List<CreateNotificationResponse> getAllNotifications(){

        return notificationService.getAllNotifications();

    }

    //obtiene uno por id
    @GetMapping("/{id}")
    @Operation(summary = "buscar notificacion por id")
    public ResponseEntity<CreateNotificationResponse> getNotificationById(@PathVariable Integer id){

        CreateNotificationResponse notificationResponse = notificationService.getNotificationById(id);

        return new ResponseEntity<>(
                notificationResponse,
                HttpStatus.CREATED
        );

    }


    //crea
    @PostMapping("/create")
    @Operation(summary = "crear notificacion")
    public ResponseEntity<CreateNotificationResponse> createNotification(
            @Valid @RequestBody CreateNotificationRequest createNotificationRequest
    ) throws Exception {

        CreateNotificationResponse notificationCreated = notificationService.createNotification(createNotificationRequest);

        return new ResponseEntity<>(
                notificationCreated,
                HttpStatus.CREATED
        );
    }

    //actualiza por id
    @PutMapping("/update/{id}")
    @Operation(summary = "actualizar notificacion")
    public ResponseEntity<UpdateNotificationResponse> updateNotification(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateNotificationRequest updateNotificationRequest
    ) throws Exception {

        //llama update en service
        UpdateNotificationResponse notificationUpdated = notificationService.updateNotification(id, updateNotificationRequest);

        //retorna response
        return new ResponseEntity<>(
                notificationUpdated,
                HttpStatus.CREATED
        );
    }

    //elimina notificacion por id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "eliminar notifcation")
    public ResponseEntity<String> deleteNotification(@PathVariable Integer id) throws Exception {

        //llama service delete
        notificationService.deleteNotification(id);

        //retorna mensaje
        return new ResponseEntity<>(
                "Notification eliminado correctamente",
                HttpStatus.OK
        );
    }


}
