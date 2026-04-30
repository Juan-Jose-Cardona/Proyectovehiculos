package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateAuditLogRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateAuditLogResponse;
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

    private final AuditLogService auditLogService;

    @GetMapping("/all")
    public List<CreateAuditLogResponse> getAllAuditLogs() {
        return auditLogService.getAllAuditLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateAuditLogResponse> getAuditLogById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(auditLogService.getAuditLogById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateAuditLogResponse> createAuditLog(@RequestBody CreateAuditLogRequest request) throws Exception {
        return new ResponseEntity<>(auditLogService.createAuditLog(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateAuditLogResponse> updateAuditLog(
            @PathVariable Long id,
            @RequestBody UpdateAuditLogRequest request
    ) throws Exception {
        return new ResponseEntity<>(auditLogService.updateAuditLog(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuditLog(@PathVariable Long id) throws Exception {
        auditLogService.deleteAuditLog(id);
        return new ResponseEntity<>("AuditLog eliminado correctamente", HttpStatus.OK);
    }
}