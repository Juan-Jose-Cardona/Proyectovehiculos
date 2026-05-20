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

@RestController
@RequestMapping("/obligation-rule-notify-days")
@AllArgsConstructor
public class ObligationRuleNotifyDayController {

    private final ObligationRuleNotifyDayService obligationRuleNotifyDayService;

    //obtiene lista
    @GetMapping("/all")
    public ResponseEntity<?> getAllObligationRuleNotifyDays() {

        //retorna lista
        return new ResponseEntity<>(obligationRuleNotifyDayService.getAllObligationRuleNotifyDays(), HttpStatus.OK);
    }

    //obtiene segun id
    @GetMapping("/{id}")
    public ResponseEntity<?> getObligationRuleNotifyDayById(@PathVariable Integer id) {

        //retorna objeto
        return new ResponseEntity<>(obligationRuleNotifyDayService.getObligationRuleNotifyDayById(id), HttpStatus.OK
        );
    }

    //crea obligation rule notify day
    @PostMapping("/create")
    public ResponseEntity<CreateObligationRuleNotifyDayResponse> createObligationRuleNotifyDay(@RequestBody CreateObligationRuleNotifyDayRequest createObligationRuleNotifyDayRequest) throws Exception {

        //llama metodo service create
        CreateObligationRuleNotifyDayResponse obligationRuleNotifyDayCreated = obligationRuleNotifyDayService.createObligationRuleNotifyDay(createObligationRuleNotifyDayRequest);

        //retorna response entity
        return new ResponseEntity<>(obligationRuleNotifyDayCreated, HttpStatus.CREATED);
    }

    //actualiza obligation rule notify day
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateObligationRuleNotifyDayResponse> updateObligationRuleNotifyDay(@PathVariable Integer id, @RequestBody UpdateObligationRuleNotifyDayRequest updateObligationRuleNotifyDayRequest) throws Exception {

        //llama metodo service update
        UpdateObligationRuleNotifyDayResponse obligationRuleNotifyDayUpdated = obligationRuleNotifyDayService.updateObligationRuleNotifyDay(id, updateObligationRuleNotifyDayRequest);

        //retorna response entity
        return new ResponseEntity<>(obligationRuleNotifyDayUpdated, HttpStatus.CREATED);
    }

}
