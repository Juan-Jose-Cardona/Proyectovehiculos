package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateVehicleResponse;
import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class VehicleMapper {

    public static List<CreateVehicleResponse> entityToListCreateVehicleResponse(List<Vehicle> vehicles) {
        return vehicles.stream()
                .map(VehicleMapper::entityToCreateVehicleResponse)
                .toList();
    }

    public static CreateVehicleResponse entityToCreateVehicleResponse(Vehicle vehicle) {
        return CreateVehicleResponse.builder()
                .id(vehicle.getId())
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

}
