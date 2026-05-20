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

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/obligations")
public class ObligationController {

    private final ObligationService obligationService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }


    //obtiene lista obligaciones
    @GetMapping("/all")
    public List<CreateObligationResponse> getAllObligations(){

        return obligationService.getAllObligations();

    }


    //toma por id
    @GetMapping("/{id}")
    public ResponseEntity<CreateObligationResponse> getObligationById(@PathVariable Integer id){

        CreateObligationResponse obligationResponse = obligationService.getObligationById(id);

        return new ResponseEntity<>(
                obligationResponse,
                HttpStatus.CREATED
        );

    }

    //crea obligacion por post
    @PostMapping("/create")
    public ResponseEntity<CreateObligationResponse> createObligation(
            @RequestBody CreateObligationRequest createObligationRequest
    ) throws Exception {

        CreateObligationResponse obligationCreated = obligationService.createObligation(createObligationRequest);

        return new ResponseEntity<>(
                obligationCreated,
                HttpStatus.CREATED
        );
    }


    //actualiza
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateObligationResponse> updateObligation(
            @PathVariable Integer id,
            @RequestBody UpdateObligationRequest updateObligationRequest
    ) throws Exception {

        //llama metodo service update
        UpdateObligationResponse obligationUpdated = obligationService.updateObligation(id, updateObligationRequest);

        //retorna response entity
        return new ResponseEntity<>(
                obligationUpdated,
                HttpStatus.CREATED
        );
    }

}
