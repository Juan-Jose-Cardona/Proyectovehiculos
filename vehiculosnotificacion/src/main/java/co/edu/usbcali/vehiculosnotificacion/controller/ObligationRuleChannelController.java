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

@RestController
@RequestMapping("/obligation-rule-channels")
@AllArgsConstructor
public class ObligationRuleChannelController {

    private final ObligationRuleChannelService obligationRuleChannelService;

    //obtiene lista
    @GetMapping("/all")
    public ResponseEntity<?> getAllObligationRuleChannels() {

        //retorna lista
        return new ResponseEntity<>(
                obligationRuleChannelService.getAllObligationRuleChannels(),
                HttpStatus.OK
        );
    }

    //obtiene segun id
    @GetMapping("/{id}")
    public ResponseEntity<?> getObligationRuleChannelById(@PathVariable Integer id) {

        //retorna objeto
        return new ResponseEntity<>(
                obligationRuleChannelService.getObligationRuleChannelById(id),
                HttpStatus.OK
        );
    }

    //crea obligation rule channel
    @PostMapping("/create")
    public ResponseEntity<CreateObligationRuleChannelResponse> createObligationRuleChannel(
            @RequestBody CreateObligationRuleChannelRequest createObligationRuleChannelRequest
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

    //actualiza obligation rule channel
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateObligationRuleChannelResponse> updateObligationRuleChannel(
            @PathVariable Integer id,
            @RequestBody UpdateObligationRuleChannelRequest updateObligationRuleChannelRequest
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


}
