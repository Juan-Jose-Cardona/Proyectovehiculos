package co.edu.usbcali.vehiculosnotificacion.service.impl;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateVehicleResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateVehicleResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.mapper.VehicleMapper;
import co.edu.usbcali.vehiculosnotificacion.model.User;
import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;
import co.edu.usbcali.vehiculosnotificacion.repository.UserRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.VehicleRepository;
import co.edu.usbcali.vehiculosnotificacion.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    //obtiene lista vehiculos
    @Override
    public List<CreateVehicleResponse> getAllVehicles() {

        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<CreateVehicleResponse> createVehicleResponseList = VehicleMapper.entityToListCreateVehicleResponse(vehicles);
        return createVehicleResponseList;

    }

    //obtiene vehiculo segun id
    @Override
    public CreateVehicleResponse getVehicleById(Integer id) {

        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));
        CreateVehicleResponse createVehicleResponse = VehicleMapper.entityToCreateVehicleResponse(vehicle);
        return createVehicleResponse;
    }

    //crea vehiculo
    @Override
    public CreateVehicleResponse createVehicle(CreateVehicleRequest createVehicleRequest) throws Exception {

        try {
            if (createVehicleRequest == null) {
                throw new Exception("El objeto CreateVehicleRequest no puede ser nulo");
            }

            if (createVehicleRequest.getUserId() == null || createVehicleRequest.getUserId() <= 0) {
                throw new Exception("El userId es requerido y debe ser mayor a 0");
            }

            if (createVehicleRequest.getPlate() == null || createVehicleRequest.getPlate().isBlank()) {
                throw new Exception("La placa es requerida");
            }

            if (createVehicleRequest.getPlate().length() > 20) {
                throw new Exception("La placa soporta hasta 20 caracteres");
            }

            if (createVehicleRequest.getVehicleType() == null || createVehicleRequest.getVehicleType().isBlank()) {
                throw new Exception("El tipo de vehiculo es requerido");
            }

            User user = userRepository.findById(createVehicleRequest.getUserId())
                    .orElseThrow(() -> new Exception(
                            "No se ha encontrado el user con el id " + createVehicleRequest.getUserId()
                    ));

            boolean vehicleAlreadyExists = vehicleRepository.existsByUserIdAndPlate(
                    createVehicleRequest.getUserId(),
                    createVehicleRequest.getPlate()
            );

            if (vehicleAlreadyExists) {
                throw new Exception("Ya existe un vehiculo con esa placa para este usuario");
            }

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

        } catch (Exception e) {
            throw e;
        }
    }

    //metodo para actualizar atributos
    @Override
    public UpdateVehicleResponse updateVehicle(Integer id, UpdateVehicleRequest updateVehicleRequest) throws Exception {

        try {


            // Validar id no nulo
            if (id == null){
                throw new Exception("El objeto Vehicle debe existir");
            }


            //valida request no nulo
            if (updateVehicleRequest == null){
                throw new Exception("El objeto UpdateVehicleRequest no puede ser nulo");
            }

            //busca vehiculo por id
            Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found with id; " + id));

            //actualiza usuario vehiculo
            if (updateVehicleRequest.getUserId() != null) {

                //busca usuario por id
                User user = userRepository.findById(updateVehicleRequest.getUserId())
                        .orElseThrow(() -> new Exception(
                                "No se encontro el user con id " + updateVehicleRequest.getUserId()
                        ));

                //asigna usuario encontrado
                vehicle.setUser(user);
            }

            //actualiza placa
            if (updateVehicleRequest.getPlate() != null) {
                vehicle.setPlate(updateVehicleRequest.getPlate());
            }

            //actualiza marca
            if (updateVehicleRequest.getBrand() != null) {
                vehicle.setBrand(updateVehicleRequest.getBrand());
            }

            //actualiza linea modelo
            if (updateVehicleRequest.getLineModel() != null) {
                vehicle.setLineModel(updateVehicleRequest.getLineModel());
            }

            //actualiza modelo
            if (updateVehicleRequest.getModelYear() != null) {
                vehicle.setModelYear(updateVehicleRequest.getModelYear());
            }

            //actualiza tipo vehiculo
            if (updateVehicleRequest.getVehicleType() != null) {
                vehicle.setVehicleType(updateVehicleRequest.getVehicleType());
            }

            //actualiza notas
            if (updateVehicleRequest.getNotes() != null) {
                vehicle.setNotes(updateVehicleRequest.getNotes());
            }

            //guarda entidad actualizada
            vehicle = vehicleRepository.save(vehicle);

            //convierte a update response
            UpdateVehicleResponse response = VehicleMapper.entityToUpdateVehicleResponse(vehicle);

            //retorna dto
            return response;

        } catch (Exception e) {
            throw e;
        }
    }


    //metodo para eliminar vehicle
    @Override
    public void deleteVehicle(Integer id) throws Exception {

        try {

            //valida id no nulo
            if (id == null){
                throw new Exception("El id del vehicle es requerido");
            }

            //busca usuario por id
            Vehicle vehicle = vehicleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));

            //elimina usuario
            vehicleRepository.delete(vehicle);

        } catch (Exception e) {
            throw e;
        }
    }


}
