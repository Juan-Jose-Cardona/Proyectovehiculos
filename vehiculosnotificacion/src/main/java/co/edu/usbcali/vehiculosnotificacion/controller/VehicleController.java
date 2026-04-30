package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateVehicleResponse;
import co.edu.usbcali.vehiculosnotificacion.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/all")
    public List<CreateVehicleResponse> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateVehicleResponse> getVehicleById(@PathVariable Integer id) throws Exception {

        CreateVehicleResponse vehicleResponse = vehicleService.getVehicleById(id);

        return new ResponseEntity<>(
                vehicleResponse,
                HttpStatus.OK
        );
    }

    @PostMapping("/create")
    public ResponseEntity<CreateVehicleResponse> createVehicle(
            @RequestBody CreateVehicleRequest createVehicleRequest
    ) throws Exception {

        CreateVehicleResponse vehicleCreated = vehicleService.createVehicle(createVehicleRequest);

        return new ResponseEntity<>(
                vehicleCreated,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateVehicleResponse> updateVehicle(
            @PathVariable Integer id,
            @RequestBody UpdateVehicleRequest updateVehicleRequest
    ) throws Exception {

        CreateVehicleResponse vehicleUpdated = vehicleService.updateVehicle(id, updateVehicleRequest);

        return new ResponseEntity<>(
                vehicleUpdated,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Integer id) throws Exception {

        vehicleService.deleteVehicle(id);

        return new ResponseEntity<>(
                "Vehiculo eliminado correctamente",
                HttpStatus.OK
        );
    }
}