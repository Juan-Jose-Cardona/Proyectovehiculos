package co.edu.usbcali.vehiculosnotificacion.service;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateVehicleResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateVehicleResponse;

import java.util.List;

public interface VehicleService {

    CreateVehicleResponse createVehicle(CreateVehicleRequest createVehicleRequest) throws Exception;

    List<CreateVehicleResponse> getAllVehicles();

    CreateVehicleResponse getVehicleById(Integer id) throws Exception;

    CreateVehicleResponse updateVehicle(Integer id, UpdateVehicleRequest updateVehicleRequest) throws Exception;

    void deleteVehicle(Integer id) throws Exception;

}
