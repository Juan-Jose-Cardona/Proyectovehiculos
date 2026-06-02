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

//importa valid
import jakarta.validation.Valid;

//importa para agregar documentacion de swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/audit-logs")
@Tag(name = "audit-logs", description = "operaciones de auditoria")
public class AuditLogController {

    //declara objeto de AuditService
    private final AuditLogService auditLogService;


    @GetMapping("/ping")
    @Operation(summary = "verificar auditoria")
    public String ping() {
        return "pong";
    }

    //lista
    @GetMapping("/all")
    @Operation(summary = "listar auditoria")
    public List<CreateAuditLogResponse> getAllAuditLogs(){

        return auditLogService.getAllAuditLogs();

    }

    //coge por id
    @GetMapping("/{id}")
    @Operation(summary = "buscar auditoria por id")
    public ResponseEntity<CreateAuditLogResponse> getAuditLogById(@PathVariable Long id){

        CreateAuditLogResponse auditLogResponse = auditLogService.getAuditLogById(id);

        return new ResponseEntity<>(
                auditLogResponse,
                HttpStatus.CREATED
        );

    }

    //crea
    @PostMapping("/create")
    @Operation(summary = "crear auditoria")
    public ResponseEntity<CreateAuditLogResponse> createAuditLog(@Valid @RequestBody CreateAuditLogRequest createAuditLogRequest) throws Exception {

        CreateAuditLogResponse auditLogCreated = auditLogService.createAuditLog(createAuditLogRequest);

        return new ResponseEntity<>(
                auditLogCreated,
                HttpStatus.CREATED
        );
    }

    //actualiza
    @PutMapping("/update/{id}")
    @Operation(summary = "actualizar auditoria")
    public ResponseEntity<UpdateAuditLogResponse> updateAuditLog(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAuditLogRequest updateAuditLogRequest
    ) throws Exception {

        //llama metodo update en service
        UpdateAuditLogResponse auditLogUpdated = auditLogService.updateAuditLog(id, updateAuditLogRequest);

        //retorna el response
        return new ResponseEntity<>(
                auditLogUpdated,
                HttpStatus.CREATED
        );
    }


    //delete
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "eliminar auditLog")
    public ResponseEntity<String> deleteAuditlog(@PathVariable Integer id) throws Exception {

        //llama service delete
        auditLogService.deleteAuditLog(id);

        //retorna mensaje
        return new ResponseEntity<>(
                "AuditLog eliminado correctamente",
                HttpStatus.OK
        );
    }


}
