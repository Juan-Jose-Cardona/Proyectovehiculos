package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationResponse;
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

    @GetMapping("/all")
    public List<CreateObligationResponse> getAllObligations() {
        return obligationService.getAllObligations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateObligationResponse> getObligationById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(obligationService.getObligationById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateObligationResponse> createObligation(@RequestBody CreateObligationRequest request) throws Exception {
        return new ResponseEntity<>(obligationService.createObligation(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateObligationResponse> updateObligation(
            @PathVariable Integer id,
            @RequestBody UpdateObligationRequest request
    ) throws Exception {
        return new ResponseEntity<>(obligationService.updateObligation(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteObligation(@PathVariable Integer id) throws Exception {
        obligationService.deleteObligation(id);
        return new ResponseEntity<>("Obligation eliminada correctamente", HttpStatus.OK);
    }
}