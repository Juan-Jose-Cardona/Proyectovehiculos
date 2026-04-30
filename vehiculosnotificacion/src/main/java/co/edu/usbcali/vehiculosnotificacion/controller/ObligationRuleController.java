package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleResponse;
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

    @GetMapping("/all")
    public List<CreateObligationRuleResponse> getAllObligationRules() {
        return obligationRuleService.getAllObligationRules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateObligationRuleResponse> getObligationRuleById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(obligationRuleService.getObligationRuleById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateObligationRuleResponse> createObligationRule(
            @RequestBody CreateObligationRuleRequest request
    ) throws Exception {
        return new ResponseEntity<>(obligationRuleService.createObligationRule(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateObligationRuleResponse> updateObligationRule(
            @PathVariable Integer id,
            @RequestBody UpdateObligationRuleRequest request
    ) throws Exception {
        return new ResponseEntity<>(obligationRuleService.updateObligationRule(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteObligationRule(@PathVariable Integer id) throws Exception {
        obligationRuleService.deleteObligationRule(id);
        return new ResponseEntity<>("ObligationRule eliminada correctamente", HttpStatus.OK);
    }
}