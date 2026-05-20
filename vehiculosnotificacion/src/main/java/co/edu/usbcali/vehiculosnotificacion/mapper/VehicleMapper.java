package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateVehicleResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateVehicleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateVehicleResponse;
import co.edu.usbcali.vehiculosnotificacion.model.User;
import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.List;

@Getter
@AllArgsConstructor
public class VehicleMapper {

    //asigna valores para crear objeto
    public static CreateVehicleResponse entityToCreateVehicleResponse(Vehicle vehicle) {
        return CreateVehicleResponse.builder()
                .id(vehicle.getId())
                .userId(Objects.nonNull(vehicle.getUser()) ? vehicle.getUser().getId() : null)
                .userName(Objects.nonNull(vehicle.getUser()) ? vehicle.getUser().getFullName() : null)
                .plate(vehicle.getPlate())
                .brand(vehicle.getBrand())
                .lineModel(vehicle.getLineModel())
                .modelYear(vehicle.getModelYear())
                .vehicleType(vehicle.getVehicleType())
                .notes(vehicle.getNotes())
                .createdAt(vehicle.getCreatedAt())
                .updatedAt(vehicle.getUpdatedAt())
                .build();
    }

    //convierte lista
    public static List<CreateVehicleResponse> entityToListCreateVehicleResponse(List<Vehicle> vehicles) {
        return vehicles.stream().map(VehicleMapper::entityToCreateVehicleResponse).toList();
    }

    //convierte request a entidad
    public static Vehicle createVehicleRequestToEntity(CreateVehicleRequest createVehicleRequest, User user){

        //construye entidad vehicle desde request
        return Vehicle.builder()
                .user(user)
                .plate(createVehicleRequest.getPlate())
                .brand(createVehicleRequest.getBrand())
                .lineModel(createVehicleRequest.getLineModel())
                .modelYear(createVehicleRequest.getModelYear())
                .vehicleType(createVehicleRequest.getVehicleType())
                .notes(createVehicleRequest.getNotes())
                .build();
    }

    //convierte entidad a update
    public static UpdateVehicleResponse entityToUpdateVehicleResponse(Vehicle vehicle) {

        //instanciar nuevo objeto
        UpdateVehicleResponse response = UpdateVehicleResponse.builder()
                .id(vehicle.getId())
                .userId(Objects.nonNull(vehicle.getUser()) ? vehicle.getUser().getId() : null)
                .userName(Objects.nonNull(vehicle.getUser()) ? vehicle.getUser().getFullName() : null)
                .plate(vehicle.getPlate())
                .brand(vehicle.getBrand())
                .lineModel(vehicle.getLineModel())
                .modelYear(vehicle.getModelYear())
                .vehicleType(vehicle.getVehicleType())
                .notes(vehicle.getNotes())
                .createdAt(vehicle.getCreatedAt())
                .updatedAt(vehicle.getUpdatedAt())
                .build();


        return response;
    }


}
