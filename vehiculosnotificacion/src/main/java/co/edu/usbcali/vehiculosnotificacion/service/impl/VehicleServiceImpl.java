package co.edu.usbcali.vehiculosnotificacion.service.impl;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateVehicleResponse;
import co.edu.usbcali.vehiculosnotificacion.mapper.VehicleMapper;
import co.edu.usbcali.vehiculosnotificacion.model.User;
import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;
import co.edu.usbcali.vehiculosnotificacion.repository.UserRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.VehicleRepository;
import co.edu.usbcali.vehiculosnotificacion.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    @Override
    public CreateVehicleResponse createVehicle(CreateVehicleRequest createVehicleRequest) throws Exception {

        if (Objects.isNull(createVehicleRequest)) {
            throw new Exception("El objeto CreateVehicleRequest no puede ser nulo");
        }

        if (Objects.isNull(createVehicleRequest.getUserId()) || createVehicleRequest.getUserId() <= 0) {
            throw new Exception("El userId es requerido y debe ser mayor a 0");
        }

        if (Objects.isNull(createVehicleRequest.getPlate()) || createVehicleRequest.getPlate().isBlank()) {
            throw new Exception("La placa es requerida");
        }

        if (createVehicleRequest.getPlate().length() > 20) {
            throw new Exception("La placa soporta hasta 20 caracteres");
        }

        if (Objects.isNull(createVehicleRequest.getVehicleType()) || createVehicleRequest.getVehicleType().isBlank()) {
            throw new Exception("El tipo de vehiculo es requerido");
        }

        if (createVehicleRequest.getVehicleType().length() > 50) {
            throw new Exception("El tipo de vehiculo soporta hasta 50 caracteres");
        }

        User user = userRepository.findById(createVehicleRequest.getUserId())
                .orElseThrow(() -> new Exception(
                        "No se ha encontrado el user con el id " + createVehicleRequest.getUserId()
                ));

        Vehicle vehicle = Vehicle.builder()
                .user(user)
                .plate(createVehicleRequest.getPlate())
                .brand(createVehicleRequest.getBrand())
                .lineModel(createVehicleRequest.getLineModel())
                .modelYear(createVehicleRequest.getModelYear())
                .vehicleType(createVehicleRequest.getVehicleType())
                .notes(createVehicleRequest.getNotes())
                .createdAt(createVehicleRequest.getCreatedAt())
                .updatedAt(createVehicleRequest.getUpdatedAt())
                .build();

        vehicle = vehicleRepository.save(vehicle);

        return VehicleMapper.entityToCreateVehicleResponse(vehicle);
    }

    @Override
    public List<CreateVehicleResponse> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return VehicleMapper.entityToListCreateVehicleResponse(vehicles);
    }

    @Override
    public CreateVehicleResponse getVehicleById(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el vehicle con id " + id));

        return VehicleMapper.entityToCreateVehicleResponse(vehicle);
    }

    @Override
    public CreateVehicleResponse updateVehicle(Integer id, UpdateVehicleRequest updateVehicleRequest) throws Exception {

        try{

            if (id == null || id <= 0) {
                throw new Exception("El id es requerido");
            }

            if (updateVehicleRequest == null) {
                throw new Exception("El objeto UpdateVehicleRequest no puede ser nulo");
            }

            Vehicle vehicle = vehicleRepository.findById(id)
                    .orElseThrow(() -> new Exception("No se encontro el vehicle con id " + id));

            if (updateVehicleRequest.getUserId() != null) {
                if (updateVehicleRequest.getUserId() <= 0) {
                    throw new Exception("El userId debe ser mayor a 0");
                }

                User user = userRepository.findById(updateVehicleRequest.getUserId())
                        .orElseThrow(() -> new Exception(
                                "No se encontro el user con id " + updateVehicleRequest.getUserId()
                        ));

                vehicle.setUser(user);
            }

            if (updateVehicleRequest.getPlate() != null) {
                if (updateVehicleRequest.getPlate().isBlank()) {
                    throw new Exception("La placa no puede estar vacia");
                }

                if (updateVehicleRequest.getPlate().length() > 20) {
                    throw new Exception("La placa soporta hasta 20 caracteres");
                }

                vehicle.setPlate(updateVehicleRequest.getPlate());
            }

            if (updateVehicleRequest.getBrand() != null) {
                if (updateVehicleRequest.getBrand().length() > 100) {
                    throw new Exception("La marca soporta hasta 100 caracteres");
                }

                vehicle.setBrand(updateVehicleRequest.getBrand());
            }

            if (updateVehicleRequest.getLineModel() != null) {
                if (updateVehicleRequest.getLineModel().length() > 100) {
                    throw new Exception("La linea/modelo soporta hasta 100 caracteres");
                }

                vehicle.setLineModel(updateVehicleRequest.getLineModel());
            }

            if (updateVehicleRequest.getModelYear() != null) {
                vehicle.setModelYear(updateVehicleRequest.getModelYear());
            }

            if (updateVehicleRequest.getVehicleType() != null) {
                if (updateVehicleRequest.getVehicleType().isBlank()) {
                    throw new Exception("El tipo de vehiculo no puede estar vacio");
                }

                if (updateVehicleRequest.getVehicleType().length() > 50) {
                    throw new Exception("El tipo de vehiculo soporta hasta 50 caracteres");
                }

                vehicle.setVehicleType(updateVehicleRequest.getVehicleType());
            }

            if (updateVehicleRequest.getNotes() != null) {
                vehicle.setNotes(updateVehicleRequest.getNotes());
            }

            vehicle = vehicleRepository.save(vehicle);

            return VehicleMapper.entityToCreateVehicleResponse(vehicle);

        } catch (Exception e) {

            throw e;

        }


    }



    @Override
    public void deleteVehicle(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el vehicle con id " + id));

        vehicleRepository.delete(vehicle);
    }
}