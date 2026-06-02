package co.edu.usbcali.vehiculosnotificacion.controller;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleNotifyDayRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleNotifyDayRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleNotifyDayResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleNotifyDayResponse;
import co.edu.usbcali.vehiculosnotificacion.service.ObligationRuleNotifyDayService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//importa valid
import jakarta.validation.Valid;

//importa para agregar documentacion de swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/obligation-rule-notify-days")
@AllArgsConstructor
@Tag(name = "obligation-rule-notify-days", description = "operaciones de dias de notificacion")
public class ObligationRuleNotifyDayController {

    private final ObligationRuleNotifyDayService obligationRuleNotifyDayService;

    //obtiene lista
    @GetMapping("/all")
    @Operation(summary = "listar dias de notificacion")
    public ResponseEntity<?> getAllObligationRuleNotifyDays() {

        //retorna lista
        return new ResponseEntity<>(obligationRuleNotifyDayService.getAllObligationRuleNotifyDays(), HttpStatus.OK);
    }

    //obtiene segun id
    @GetMapping("/{id}")
    @Operation(summary = "buscar dia por id")
    public ResponseEntity<?> getObligationRuleNotifyDayById(@PathVariable Integer id) {

        //retorna objeto
        return new ResponseEntity<>(obligationRuleNotifyDayService.getObligationRuleNotifyDayById(id), HttpStatus.OK
        );
    }

    //crea obligation rule notify day
    @PostMapping("/create")
    @Operation(summary = "crear dia")
    public ResponseEntity<CreateObligationRuleNotifyDayResponse> createObligationRuleNotifyDay(
            @Valid @RequestBody CreateObligationRuleNotifyDayRequest createObligationRuleNotifyDayRequest
    ) throws Exception {

        //llama metodo service create
        CreateObligationRuleNotifyDayResponse obligationRuleNotifyDayCreated =
                obligationRuleNotifyDayService.createObligationRuleNotifyDay(createObligationRuleNotifyDayRequest);

        //retorna response entity
        return new ResponseEntity<>(
                obligationRuleNotifyDayCreated,
                HttpStatus.CREATED
        );
    }

    //actualiza obligation rule notify day
    @PutMapping("/update/{id}")
    @Operation(summary = "actualizar dia")
    public ResponseEntity<UpdateObligationRuleNotifyDayResponse> updateObligationRuleNotifyDay(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateObligationRuleNotifyDayRequest updateObligationRuleNotifyDayRequest
    ) throws Exception {

        //llama metodo service update
        UpdateObligationRuleNotifyDayResponse obligationRuleNotifyDayUpdated =
                obligationRuleNotifyDayService.updateObligationRuleNotifyDay(id, updateObligationRuleNotifyDayRequest);

        //retorna response entity
        return new ResponseEntity<>(
                obligationRuleNotifyDayUpdated,
                HttpStatus.CREATED
        );
    }

    //elimina ObligationRuleNotifyDay por id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "eliminar usuario")
    public ResponseEntity<String> deleteObligationRuleNotifyDay(@PathVariable Integer id) throws Exception {

        //llama service delete
        obligationRuleNotifyDayService.deleteObligationRuleNotifyDay(id);

        //retorna mensaje
        return new ResponseEntity<>(
                "ObligationRuleNotifyDay eliminado correctamente",
                HttpStatus.OK
        );
    }


}
