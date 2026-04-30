package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationResponse;
import co.edu.usbcali.vehiculosnotificacion.model.Obligation;
import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;

import java.util.List;
import java.util.Objects;

public class ObligationMapper {

    public static CreateObligationResponse entityToCreateObligationResponse(Obligation obligation) {
        return CreateObligationResponse.builder()
                .id(obligation.getId())
                .vehiclePlate(Objects.nonNull(obligation.getVehicle()) ? obligation.getVehicle().getPlate() : null)
                .type(obligation.getType())
                .dueDate(obligation.getDueDate())
                .status(obligation.getStatus())
                .lastCalcAt(obligation.getLastCalcAt())
                .notes(obligation.getNotes())
                .createdAt(obligation.getCreatedAt())
                .updatedAt(obligation.getUpdatedAt())
                .build();
    }

    public static Obligation createObligationRequestToEntity(
            CreateObligationRequest createObligationRequest,
            Vehicle vehicle
    ) {
        return Obligation.builder()
                .vehicle(vehicle)
                .type(createObligationRequest.getType())
                .dueDate(createObligationRequest.getDueDate())
                .status(createObligationRequest.getStatus())
                .lastCalcAt(createObligationRequest.getLastCalcAt())
                .notes(createObligationRequest.getNotes())
                .build();
    }

    public static List<CreateObligationResponse> entityToListCreateObligationResponse(List<Obligation> obligations) {
        return obligations.stream()
                .map(ObligationMapper::entityToCreateObligationResponse)
                .toList();
    }

}
