package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationResponse;
import co.edu.usbcali.vehiculosnotificacion.service.ObligationService;
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
@RequestMapping("/obligations")
@Tag(name = "obligations", description = "operaciones de obligaciones")
public class ObligationController {

    private final ObligationService obligationService;

    @GetMapping("/ping")
    @Operation(summary = "verificar obligaciones")
    public String ping() {
        return "pong";
    }


    //obtiene lista obligaciones
    @GetMapping("/all")
    @Operation(summary = "listar obligaciones")
    public List<CreateObligationResponse> getAllObligations(){

        return obligationService.getAllObligations();

    }


    //toma por id
    @GetMapping("/{id}")
    @Operation(summary = "buscar obligacion por id")
    public ResponseEntity<CreateObligationResponse> getObligationById(@PathVariable Integer id){

        CreateObligationResponse obligationResponse = obligationService.getObligationById(id);

        return new ResponseEntity<>(
                obligationResponse,
                HttpStatus.CREATED
        );

    }

    //crea obligacion por post
    @PostMapping("/create")
    @Operation(summary = "crear obligacion")
    public ResponseEntity<CreateObligationResponse> createObligation(
            @Valid @RequestBody CreateObligationRequest createObligationRequest
    ) throws Exception {

        CreateObligationResponse obligationCreated = obligationService.createObligation(createObligationRequest);

        return new ResponseEntity<>(
                obligationCreated,
                HttpStatus.CREATED
        );
    }


    //actualiza
    @PutMapping("/update/{id}")
    @Operation(summary = "actualizar obligacion")
    public ResponseEntity<UpdateObligationResponse> updateObligation(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateObligationRequest updateObligationRequest
    ) throws Exception {

        //llama metodo service update
        UpdateObligationResponse obligationUpdated =
                obligationService.updateObligation(id, updateObligationRequest);

        //retorna response entity
        return new ResponseEntity<>(
                obligationUpdated,
                HttpStatus.CREATED
        );
    }


    //elimina obligation por id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "eliminar Obligation")
    public ResponseEntity<String> deleteObligation(@PathVariable Integer id) throws Exception {

        //llama service delete
        obligationService.deleteObligation(id);

        //retorna mensaje
        return new ResponseEntity<>(
                "Obligation eliminado correctamente",
                HttpStatus.OK
        );
    }


}
