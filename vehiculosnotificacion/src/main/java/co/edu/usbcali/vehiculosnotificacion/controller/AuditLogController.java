package co.edu.usbcali.vehiculosnotificacion.controller;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateAuditLogResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateAuditLogResponse;
import co.edu.usbcali.vehiculosnotificacion.service.AuditLogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/audit-logs")
public class AuditLogController {

    //declara objeto de AuditService
    private final AuditLogService auditLogService;


    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    //lista
    @GetMapping("/all")
    public List<CreateAuditLogResponse> getAllAuditLogs(){

        return auditLogService.getAllAuditLogs();

    }

    //coge por id
    @GetMapping("/{id}")
    public ResponseEntity<CreateAuditLogResponse> getAuditLogById(@PathVariable Long id){

        CreateAuditLogResponse auditLogResponse = auditLogService.getAuditLogById(id);

        return new ResponseEntity<>(
                auditLogResponse,
                HttpStatus.CREATED
        );

    }

    //crea
    @PostMapping("/create")
    public ResponseEntity<CreateAuditLogResponse> createAuditLog(@RequestBody CreateAuditLogRequest createAuditLogRequest) throws Exception {

        CreateAuditLogResponse auditLogCreated = auditLogService.createAuditLog(createAuditLogRequest);

        return new ResponseEntity<>(
                auditLogCreated,
                HttpStatus.CREATED
        );
    }

    //actualiza
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateAuditLogResponse> updateAuditLog(@PathVariable Long id, @RequestBody UpdateAuditLogRequest updateAuditLogRequest) throws Exception {

        //llama metodo update en service
        UpdateAuditLogResponse auditLogUpdated = auditLogService.updateAuditLog(id, updateAuditLogRequest);

        //retorna el response
        return new ResponseEntity<>(
                auditLogUpdated,
                HttpStatus.CREATED
        );
    }



}
