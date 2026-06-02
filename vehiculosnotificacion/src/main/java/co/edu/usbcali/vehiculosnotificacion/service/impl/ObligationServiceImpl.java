package co.edu.usbcali.vehiculosnotificacion.service.impl;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationResponse;
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

    //crea obligacion
    @Override
    public CreateObligationResponse createObligation(CreateObligationRequest createObligationRequest) throws Exception {

        try {

            //valida request no nulo
            if (createObligationRequest == null) {
                throw new Exception("El objeto CreateObligationRequest no puede ser nulo");
            }

            //valida vehicle id
            if (createObligationRequest.getVehicleId() == null || createObligationRequest.getVehicleId() <= 0) {
                throw new Exception("El vehicleId es requerido");
            }

            //valida tipo obligacion
            if (createObligationRequest.getType() == null) {
                throw new Exception("El tipo de obligacion es requerido");
            }

            //valida fecha vencimiento
            if (createObligationRequest.getDueDate() == null) {
                throw new Exception("La fecha de vencimiento es requerida");
            }

            //busca vehiculo por id
            Vehicle vehicle = vehicleRepository.findById(createObligationRequest.getVehicleId())
                    .orElseThrow(() -> new Exception(
                            "No se encontro el vehicle con id " + createObligationRequest.getVehicleId()
                    ));

            //convierte request a entidad
            Obligation obligation = ObligationMapper.createObligationRequestToEntity(
                    createObligationRequest,
                    vehicle
            );

            //guarda entidad
            obligation = obligationRepository.save(obligation);

            //retorna response
            return ObligationMapper.entityToCreateObligationResponse(obligation);

        } catch (Exception e) {
            throw e;
        }
    }


    //obtiene lista
    @Override
    public List<CreateObligationResponse> getAllObligations() {

        List<Obligation> obligations = obligationRepository.findAll();
        List<CreateObligationResponse> createObligationResponseList = ObligationMapper.entityToListCreateObligationResponse(obligations);
        return createObligationResponseList;
    }

    //obtiene objeto segun id
    @Override
    public CreateObligationResponse getObligationById(Integer id) {

        Obligation obligation = obligationRepository.findById(id).
                orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));

        CreateObligationResponse createObligationResponse = ObligationMapper.entityToCreateObligationResponse(obligation);
        return createObligationResponse;
    }


    //actualizar atributos
    @Override
    public UpdateObligationResponse updateObligation(Integer id, UpdateObligationRequest updateObligationRequest) throws Exception {

        try {

            // Validar id no nulo
            if (id == null){
                throw new Exception("El objeto Obligation debe existir");
            }

            //valida request no nulo
            if (updateObligationRequest == null) {
                throw new Exception("El objeto UpdateObligationRequest no puede ser nulo");
            }

            //busca obligacion por id
            Obligation obligation = obligationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));

            //actualiza vehiculo obligacion
            if (updateObligationRequest.getVehicleId() != null) {
                Vehicle vehicle = vehicleRepository.findById(updateObligationRequest.getVehicleId())
                        .orElseThrow(() -> new Exception(
                                "No se encontro el vehicle con id " + updateObligationRequest.getVehicleId()
                        ));

                obligation.setVehicle(vehicle);
            }

            //actualiza tipo obligacion
            if (updateObligationRequest.getType() != null) {
                obligation.setType(updateObligationRequest.getType());
            }

            //actualiza fecha vencimiento
            if (updateObligationRequest.getDueDate() != null) {
                obligation.setDueDate(updateObligationRequest.getDueDate());
            }

            //actualiza estado
            if (updateObligationRequest.getStatus() != null) {
                obligation.setStatus(updateObligationRequest.getStatus());
            }

            //actualiza fecha calculo
            if (updateObligationRequest.getLastCalcAt() != null) {
                obligation.setLastCalcAt(updateObligationRequest.getLastCalcAt());
            }

            //actualiza notas
            if (updateObligationRequest.getNotes() != null) {
                obligation.setNotes(updateObligationRequest.getNotes());
            }

            //guarda entidad actualizada
            obligation = obligationRepository.save(obligation);

            //convierte a update response
            UpdateObligationResponse response = ObligationMapper.entityToUpdateObligationResponse(obligation);

            //retorna dto
            return response;

        } catch (Exception e) {
            throw e;
        }
    }

    //metodo para eliminar obligacion
    @Override
    public void deleteObligation(Integer id) throws Exception {

        try {

            //valida id no nulo
            if (id == null){
                throw new Exception("El id de la obligacion es requerido");
            }

            //busca usuario por id
            Obligation obligation = obligationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));

            //elimina obligacion
            obligationRepository.delete(obligation);

        } catch (Exception e) {
            throw e;
        }
    }



}
