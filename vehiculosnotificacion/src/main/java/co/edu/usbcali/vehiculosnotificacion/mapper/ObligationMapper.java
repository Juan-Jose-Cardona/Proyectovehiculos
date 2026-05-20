package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationResponse;
import co.edu.usbcali.vehiculosnotificacion.model.Obligation;
import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;
import java.util.List;
import java.util.Objects;


public class ObligationMapper {

    //convierte entidad a response
    public static CreateObligationResponse entityToCreateObligationResponse(Obligation obligation){

        CreateObligationResponse response = CreateObligationResponse.builder()
                .id(obligation.getId())
                .vehicleId(Objects.nonNull(obligation.getVehicle()) ? obligation.getVehicle().getId() : null)
                .vehiclePlate(Objects.nonNull(obligation.getVehicle()) ? obligation.getVehicle().getPlate() : null)
                .type(obligation.getType())
                .dueDate(obligation.getDueDate())
                .status(obligation.getStatus())
                .lastCalcAt(obligation.getLastCalcAt())
                .notes(obligation.getNotes())
                .createdAt(obligation.getCreatedAt())
                .updatedAt(obligation.getUpdatedAt())
                .build();

        return response;
    }


    //convierte lista  a response
    public static List<CreateObligationResponse> entityToListCreateObligationResponse(List<Obligation> obligations){

        return obligations.stream()
                .map(ObligationMapper::entityToCreateObligationResponse)
                .toList();
    }


    //convierte request a entidad
    public static Obligation createObligationRequestToEntity(
            CreateObligationRequest request,
            Vehicle vehicle
    ){

        return Obligation.builder()
                .vehicle(vehicle)
                .type(request.getType())
                .dueDate(request.getDueDate())
                .status(request.getStatus())
                .lastCalcAt(request.getLastCalcAt())
                .notes(request.getNotes())
                .build();
    }

    //convierte entidad a update response
    public static UpdateObligationResponse entityToUpdateObligationResponse(Obligation obligation){

        //instanciar nuevo objeto response
        UpdateObligationResponse response = UpdateObligationResponse.builder()
                .id(obligation.getId())
                .vehicleId(Objects.nonNull(obligation.getVehicle()) ? obligation.getVehicle().getId() : null)
                .vehiclePlate(Objects.nonNull(obligation.getVehicle()) ? obligation.getVehicle().getPlate() : null)
                .type(obligation.getType())
                .dueDate(obligation.getDueDate())
                .status(obligation.getStatus())
                .lastCalcAt(obligation.getLastCalcAt())
                .notes(obligation.getNotes())
                .createdAt(obligation.getCreatedAt())
                .updatedAt(obligation.getUpdatedAt())
                .build();

        //retorna response
        return response;
    }

}
