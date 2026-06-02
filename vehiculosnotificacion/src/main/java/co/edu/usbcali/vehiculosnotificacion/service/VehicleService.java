package co.edu.usbcali.vehiculosnotificacion.service;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateVehicleResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateVehicleResponse;

import java.util.List;

public interface VehicleService {

    CreateVehicleResponse createVehicle(CreateVehicleRequest createVehicleRequest) throws Exception;

    //get all
    List<CreateVehicleResponse> getAllVehicles();

    //get by id
    CreateVehicleResponse getVehicleById(Integer id);

    //put
    UpdateVehicleResponse updateVehicle(Integer id, UpdateVehicleRequest updateVehicleRequest) throws Exception;

    //delete
    void deleteVehicle(Integer id) throws Exception;

}
