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


    //obtiene lista
    @GetMapping("/all")
    public List<CreateVehicleResponse> getAllVehicles(){

        return vehicleService.getAllVehicles();

    }

    //obtiene por id
    @GetMapping("/{id}")
    public ResponseEntity<CreateVehicleResponse> getVehicleById(@PathVariable Integer id){

        CreateVehicleResponse vehicleResponse = vehicleService.getVehicleById(id);

        return new ResponseEntity<>(
                vehicleResponse,
                HttpStatus.CREATED
        );

    }

    //hace post
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

    //actualizar segun id
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateVehicleResponse> updateVehicle(@PathVariable Integer id, @RequestBody UpdateVehicleRequest updateVehicleRequest) throws Exception {

        //llama update en service
        UpdateVehicleResponse vehicleUpdated = vehicleService.updateVehicle(id, updateVehicleRequest);

        //retorna response
        return new ResponseEntity<>(
                vehicleUpdated,
                HttpStatus.CREATED
        );
    }

}
