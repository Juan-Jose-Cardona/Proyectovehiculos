package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleResponse;
import co.edu.usbcali.vehiculosnotificacion.service.ObligationRuleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/obligation-rules")
public class ObligationRuleController {

    private final ObligationRuleService obligationRuleService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    //obtiene todo
    @GetMapping("/all")
    public List<CreateObligationRuleResponse> getAllObligationRules(){

        return obligationRuleService.getAllObligationRules();

    }

    //obtiene po id
    @GetMapping("/{id}")
    public ResponseEntity<CreateObligationRuleResponse> getObligationRuleById(@PathVariable Integer id){

        CreateObligationRuleResponse obligationRuleResponse = obligationRuleService.getObligationRuleById(id);

        return new ResponseEntity<>(
                obligationRuleResponse,
                HttpStatus.CREATED
        );

    }

    //crea
    @PostMapping("/create")
    public ResponseEntity<CreateObligationRuleResponse> createObligationRule(@RequestBody CreateObligationRuleRequest createObligationRuleRequest) throws Exception {

        CreateObligationRuleResponse obligationRuleCreated = obligationRuleService.createObligationRule(createObligationRuleRequest);

        return new ResponseEntity<>(
                obligationRuleCreated,
                HttpStatus.CREATED
        );
    }

    //actualiza
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateObligationRuleResponse> updateObligationRule(@PathVariable Integer id, @RequestBody UpdateObligationRuleRequest updateObligationRuleRequest) throws Exception {

        //llama update en service
        UpdateObligationRuleResponse obligationRuleUpdated = obligationRuleService.updateObligationRule(id, updateObligationRuleRequest);

        //retorna response
        return new ResponseEntity<>(
                obligationRuleUpdated,
                HttpStatus.CREATED
        );
    }

}
