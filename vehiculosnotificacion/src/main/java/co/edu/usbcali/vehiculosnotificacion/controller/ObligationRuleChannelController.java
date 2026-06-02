package co.edu.usbcali.vehiculosnotificacion.controller;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleChannelRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleChannelRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleChannelResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleChannelResponse;
import co.edu.usbcali.vehiculosnotificacion.service.ObligationRuleChannelService;
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
@RequestMapping("/obligation-rule-channels")
@AllArgsConstructor
@Tag(name = "obligation-rule-channels", description = "operaciones de canales de reglas")
public class ObligationRuleChannelController {

    private final ObligationRuleChannelService obligationRuleChannelService;

    //obtiene lista
    @GetMapping("/all")
    @Operation(summary = "listar canales de reglas")
    public ResponseEntity<?> getAllObligationRuleChannels() {

        //retorna lista
        return new ResponseEntity<>(
                obligationRuleChannelService.getAllObligationRuleChannels(),
                HttpStatus.OK
        );
    }

    //obtiene segun id
    @GetMapping("/{id}")
    @Operation(summary = "buscar canal por id")
    public ResponseEntity<?> getObligationRuleChannelById(@PathVariable Integer id) {

        //retorna objeto
        return new ResponseEntity<>(
                obligationRuleChannelService.getObligationRuleChannelById(id),
                HttpStatus.OK
        );
    }

    //crea obligation rule channel
    @PostMapping("/create")
    @Operation(summary = "crear canal")
    public ResponseEntity<CreateObligationRuleChannelResponse> createObligationRuleChannel(
            @Valid @RequestBody CreateObligationRuleChannelRequest createObligationRuleChannelRequest
    ) throws Exception {

        //llama metodo service create
        CreateObligationRuleChannelResponse obligationRuleChannelCreated =
                obligationRuleChannelService.createObligationRuleChannel(createObligationRuleChannelRequest);

        //retorna response entity
        return new ResponseEntity<>(
                obligationRuleChannelCreated,
                HttpStatus.CREATED
        );
    }

    //put del obligation rule channel
    @PutMapping("/update/{id}")
    @Operation(summary = "actualizar canal")
    public ResponseEntity<UpdateObligationRuleChannelResponse> updateObligationRuleChannel(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateObligationRuleChannelRequest updateObligationRuleChannelRequest
    ) throws Exception {

        //llama metodo service update
        UpdateObligationRuleChannelResponse obligationRuleChannelUpdated =
                obligationRuleChannelService.updateObligationRuleChannel(id, updateObligationRuleChannelRequest);

        //retorna response entity
        return new ResponseEntity<>(
                obligationRuleChannelUpdated,
                HttpStatus.CREATED
        );
    }


    //elimina usuario por id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "eliminar obligationRuleChannel")
    public ResponseEntity<String> deleteObligationRuleChannel(@PathVariable Integer id) throws Exception {

        //llama service delete
        obligationRuleChannelService.deleteObligationRuleChannel(id);

        //retorna mensaje
        return new ResponseEntity<>(
                "ObligationRuleChannel eliminado correctamente",
                HttpStatus.OK
        );
    }



}
