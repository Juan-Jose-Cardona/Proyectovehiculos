package co.edu.usbcali.vehiculosnotificacion.controller;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateVehicleResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateVehicleResponse;
import co.edu.usbcali.vehiculosnotificacion.repository.VehicleRepository;
import co.edu.usbcali.vehiculosnotificacion.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//importa el valid
import jakarta.validation.Valid;

//importa para agregar documentacion de swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicles")
@Tag(name = "vehicles", description = "operaciones de vehiculos")
public class VehicleController {


    private final VehicleService vehicleService;

    @GetMapping("/ping")
    @Operation(summary = "verificar vehiculos")
    public String ping() {
        return "pong";
    }


    //obtiene lista
    @GetMapping("/all")
    @Operation(summary = "listar vehiculos")
    public List<CreateVehicleResponse> getAllVehicles(){

        return vehicleService.getAllVehicles();

    }

    //obtiene por id
    @GetMapping("/{id}")
    @Operation(summary = "buscar vehiculo por id")
    public ResponseEntity<CreateVehicleResponse> getVehicleById(@PathVariable Integer id){

        CreateVehicleResponse vehicleResponse = vehicleService.getVehicleById(id);

        return new ResponseEntity<>(
                vehicleResponse,
                HttpStatus.CREATED
        );

    }

    //hace post
    @PostMapping("/create")
    @Operation(summary = "crear vehiculo")
    public ResponseEntity<CreateVehicleResponse> createVehicle(
            @Valid @RequestBody CreateVehicleRequest createVehicleRequest
    ) throws Exception {

        CreateVehicleResponse vehicleCreated = vehicleService.createVehicle(createVehicleRequest);

        return new ResponseEntity<>(
                vehicleCreated,
                HttpStatus.CREATED
        );
    }

    //actualizar segun id
    @PutMapping("/update/{id}")
    @Operation(summary = "actualizar vehiculo")
    public ResponseEntity<UpdateVehicleResponse> updateVehicle(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateVehicleRequest updateVehicleRequest
    ) throws Exception {

        //llama update en service
        UpdateVehicleResponse vehicleUpdated = vehicleService.updateVehicle(id, updateVehicleRequest);

        //retorna response
        return new ResponseEntity<>(
                vehicleUpdated,
                HttpStatus.CREATED
        );
    }

    //elimina usuario por vehicle
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "eliminar vehicle")
    public ResponseEntity<String> deleteVehicle(@PathVariable Integer id) throws Exception {

        //llama service delete
        vehicleService.deleteVehicle(id);

        //retorna mensaje
        return new ResponseEntity<>(
                "Vehicle eliminado correctamente",
                HttpStatus.OK
        );
    }

}
