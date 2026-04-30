package co.edu.usbcali.vehiculosnotificacion.service.impl;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationResponse;
import co.edu.usbcali.vehiculosnotificacion.mapper.ObligationMapper;
import co.edu.usbcali.vehiculosnotificacion.model.Obligation;
import co.edu.usbcali.vehiculosnotificacion.model.Vehicle;
import co.edu.usbcali.vehiculosnotificacion.repository.ObligationRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.VehicleRepository;
import co.edu.usbcali.vehiculosnotificacion.service.ObligationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ObligationServiceImpl implements ObligationService {

    private final ObligationRepository obligationRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public CreateObligationResponse createObligation(CreateObligationRequest request) throws Exception {

        if (request == null) {
            throw new Exception("El objeto CreateObligationRequest no puede ser nulo");
        }

        if (request.getVehicleId() == null || request.getVehicleId() <= 0) {
            throw new Exception("El vehicleId es requerido");
        }

        if (request.getType() == null) {
            throw new Exception("El tipo de obligacion es requerido");
        }

        if (request.getDueDate() == null) {
            throw new Exception("La fecha de vencimiento es requerida");
        }

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new Exception("No se encontro el vehicle con id " + request.getVehicleId()));

        Obligation obligation = ObligationMapper.createObligationRequestToEntity(request, vehicle);

        obligation = obligationRepository.save(obligation);

        return ObligationMapper.entityToCreateObligationResponse(obligation);
    }

    @Override
    public List<CreateObligationResponse> getAllObligations() {
        List<Obligation> obligations = obligationRepository.findAll();
        return ObligationMapper.entityToListCreateObligationResponse(obligations);
    }

    @Override
    public CreateObligationResponse getObligationById(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        Obligation obligation = obligationRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro la obligation con id " + id));

        return ObligationMapper.entityToCreateObligationResponse(obligation);
    }

    @Override
    public CreateObligationResponse updateObligation(Integer id, UpdateObligationRequest request) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        if (request == null) {
            throw new Exception("El objeto UpdateObligationRequest no puede ser nulo");
        }

        Obligation obligation = obligationRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro la obligation con id " + id));

        if (request.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                    .orElseThrow(() -> new Exception("No se encontro el vehicle con id " + request.getVehicleId()));
            obligation.setVehicle(vehicle);
        }

        if (request.getType() != null) obligation.setType(request.getType());
        if (request.getDueDate() != null) obligation.setDueDate(request.getDueDate());
        if (request.getStatus() != null) obligation.setStatus(request.getStatus());
        if (request.getLastCalcAt() != null) obligation.setLastCalcAt(request.getLastCalcAt());
        if (request.getNotes() != null) obligation.setNotes(request.getNotes());

        obligation = obligationRepository.save(obligation);

        return ObligationMapper.entityToCreateObligationResponse(obligation);
    }

    @Override
    public void deleteObligation(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        Obligation obligation = obligationRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro la obligation con id " + id));

        obligationRepository.delete(obligation);
    }
}