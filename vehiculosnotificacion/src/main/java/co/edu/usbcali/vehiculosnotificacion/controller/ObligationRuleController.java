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

//importa valid
import jakarta.validation.Valid;

//importa para agregar documentacion de swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@AllArgsConstructor
@RequestMapping("/obligation-rules")
@Tag(name = "obligation-rules", description = "operaciones de reglas de obligaciones")
public class ObligationRuleController {

    private final ObligationRuleService obligationRuleService;

    @GetMapping("/ping")
    @Operation(summary = "verificar reglas")
    public String ping() {
        return "pong";
    }

    //obtiene todo
    @GetMapping("/all")
    @Operation(summary = "listar reglas")
    public List<CreateObligationRuleResponse> getAllObligationRules(){

        return obligationRuleService.getAllObligationRules();

    }

    //obtiene po id
    @GetMapping("/{id}")
    @Operation(summary = "buscar regla por id")
    public ResponseEntity<CreateObligationRuleResponse> getObligationRuleById(@PathVariable Integer id){

        CreateObligationRuleResponse obligationRuleResponse = obligationRuleService.getObligationRuleById(id);

        return new ResponseEntity<>(
                obligationRuleResponse,
                HttpStatus.CREATED
        );

    }

    //crea
    @PostMapping("/create")
    @Operation(summary = "crear regla")
    public ResponseEntity<CreateObligationRuleResponse> createObligationRule(
            @Valid @RequestBody CreateObligationRuleRequest createObligationRuleRequest
    ) throws Exception {

        CreateObligationRuleResponse obligationRuleCreated = obligationRuleService.createObligationRule(createObligationRuleRequest);

        return new ResponseEntity<>(
                obligationRuleCreated,
                HttpStatus.CREATED
        );
    }

    //put
    @PutMapping("/update/{id}")
    @Operation(summary = "actualizar regla")
    public ResponseEntity<UpdateObligationRuleResponse> updateObligationRule(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateObligationRuleRequest updateObligationRuleRequest
    ) throws Exception {

        //llama update en service
        UpdateObligationRuleResponse obligationRuleUpdated =
                obligationRuleService.updateObligationRule(id, updateObligationRuleRequest);

        //retorna response
        return new ResponseEntity<>(
                obligationRuleUpdated,
                HttpStatus.CREATED
        );
    }

    //elimina usuario por id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "eliminar obligationRule")
    public ResponseEntity<String> deleteObligationRule(@PathVariable Integer id) throws Exception {

        //llama service delete
        obligationRuleService.deleteObligationRule(id);

        //retorna mensaje
        return new ResponseEntity<>(
                "ObligationRule eliminado correctamente",
                HttpStatus.OK
        );
    }

}
