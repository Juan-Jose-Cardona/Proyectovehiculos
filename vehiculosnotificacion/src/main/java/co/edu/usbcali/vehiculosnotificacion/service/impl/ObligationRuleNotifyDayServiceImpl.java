package co.edu.usbcali.vehiculosnotificacion.service.impl;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleNotifyDayRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleNotifyDayRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleNotifyDayResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleNotifyDayResponse;
import co.edu.usbcali.vehiculosnotificacion.mapper.ObligationRuleNotifyDayMapper;
import co.edu.usbcali.vehiculosnotificacion.model.ObligationRule;
import co.edu.usbcali.vehiculosnotificacion.model.ObligationRuleNotifyDay;
import co.edu.usbcali.vehiculosnotificacion.repository.ObligationRuleNotifyDayRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.ObligationRuleRepository;
import co.edu.usbcali.vehiculosnotificacion.service.ObligationRuleNotifyDayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ObligationRuleNotifyDayServiceImpl implements ObligationRuleNotifyDayService {


    private final ObligationRuleNotifyDayRepository obligationRuleNotifyDayRepository;
    private final ObligationRuleRepository obligationRuleRepository;

    //crea obligation rule notify day
    @Override
    public CreateObligationRuleNotifyDayResponse createObligationRuleNotifyDay(CreateObligationRuleNotifyDayRequest createObligationRuleNotifyDayRequest) throws Exception {

        try {

            //valida request no nulo
            if (createObligationRuleNotifyDayRequest == null) {
                throw new Exception("El objeto CreateObligationRuleNotifyDayRequest no puede ser nulo");
            }

            //valida obligation rule id
            if (createObligationRuleNotifyDayRequest.getObligationRuleId() == null
                    || createObligationRuleNotifyDayRequest.getObligationRuleId() <= 0) {
                throw new Exception("El obligationRuleId es requerido");
            }

            //valida notify day
            if (createObligationRuleNotifyDayRequest.getNotifyDay() == null) {
                throw new Exception("El notifyDay es requerido");
            }

            //busca obligation rule por id
            ObligationRule obligationRule = obligationRuleRepository.findById(
                            createObligationRuleNotifyDayRequest.getObligationRuleId()
                    )
                    .orElseThrow(() -> new Exception("No se encontro la obligation rule con id " + createObligationRuleNotifyDayRequest.getObligationRuleId()
                    ));

            //convierte request a entidad
            ObligationRuleNotifyDay obligationRuleNotifyDay =
                    ObligationRuleNotifyDayMapper.createObligationRuleNotifyDayRequestToEntity(
                            createObligationRuleNotifyDayRequest,
                            obligationRule
                    );

            //guarda entidad
            obligationRuleNotifyDay = obligationRuleNotifyDayRepository.save(obligationRuleNotifyDay);

            //retorna response
            return ObligationRuleNotifyDayMapper.entityToCreateObligationRuleNotifyDayResponse(obligationRuleNotifyDay);

        } catch (Exception e) {
            throw e;
        }
    }

    //obtiene lista
    @Override
    public List<CreateObligationRuleNotifyDayResponse> getAllObligationRuleNotifyDays() {

        List<ObligationRuleNotifyDay> obligationRuleNotifyDays = obligationRuleNotifyDayRepository.findAll();
        List<CreateObligationRuleNotifyDayResponse> createObligationRuleNotifyDayResponseList =
                ObligationRuleNotifyDayMapper.entityToListCreateObligationRuleNotifyDayResponse(obligationRuleNotifyDays);
        return createObligationRuleNotifyDayResponseList;
    }

    //obtiene segun id
    @Override
    public CreateObligationRuleNotifyDayResponse getObligationRuleNotifyDayById(Integer id) {

        ObligationRuleNotifyDay obligationRuleNotifyDay = obligationRuleNotifyDayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ObligationRuleNotifyDay not found with id; " + id));

        CreateObligationRuleNotifyDayResponse createObligationRuleNotifyDayResponse =
                ObligationRuleNotifyDayMapper.entityToCreateObligationRuleNotifyDayResponse(obligationRuleNotifyDay);
        return createObligationRuleNotifyDayResponse;
    }

    //metodo para actualizar atributos
    @Override
    public UpdateObligationRuleNotifyDayResponse updateObligationRuleNotifyDay(
            Integer id,
            UpdateObligationRuleNotifyDayRequest updateObligationRuleNotifyDayRequest
    ) throws Exception {

        try {

            // Validar id no nulo
            if (id == null){
                throw new Exception("El objeto ObligationRuleNotificationDay debe existir");
            }

            //valida request no nulo
            if (updateObligationRuleNotifyDayRequest == null) {
                throw new Exception("El objeto UpdateObligationRuleNotifyDayRequest no puede ser nulo");
            }

            //busca obligation rule notify day
            ObligationRuleNotifyDay obligationRuleNotifyDay = obligationRuleNotifyDayRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ObligationRuleNotifyDay not found with id; " + id));

            //actualiza obligation rule
            if (updateObligationRuleNotifyDayRequest.getObligationRuleId() != null) {
                ObligationRule obligationRule = obligationRuleRepository.findById(
                                updateObligationRuleNotifyDayRequest.getObligationRuleId()
                        )
                        .orElseThrow(() -> new Exception(
                                "No se encontro la obligation rule con id "
                                        + updateObligationRuleNotifyDayRequest.getObligationRuleId()
                        ));

                obligationRuleNotifyDay.setObligationRule(obligationRule);
            }

            //actualiza notify day
            if (updateObligationRuleNotifyDayRequest.getNotifyDay() != null) {
                obligationRuleNotifyDay.setNotifyDay(updateObligationRuleNotifyDayRequest.getNotifyDay());
            }

            //guarda entidad actualizada
            obligationRuleNotifyDay = obligationRuleNotifyDayRepository.save(obligationRuleNotifyDay);

            //convierte a update response
            UpdateObligationRuleNotifyDayResponse response =
                    ObligationRuleNotifyDayMapper.entityToUpdateObligationRuleNotifyDayResponse(obligationRuleNotifyDay);

            //retorna dto
            return response;

        } catch (Exception e) {
            throw e;
        }
    }


    //metodo para eliminar ObligationRuleNotifyDay
    @Override
    public void deleteObligationRuleNotifyDay(Integer id) throws Exception {

        try {

            //valida id no nulo
            if (id == null){
                throw new Exception("El id del obligationRuleNotifyDay es requerido");
            }

            //busca usuario por id
            ObligationRuleNotifyDay obligationRuleNotifyDay = obligationRuleNotifyDayRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));

            //elimina obligationRuleNotifyDay
            obligationRuleNotifyDayRepository.delete(obligationRuleNotifyDay);

        } catch (Exception e) {
            throw e;
        }
    }



}
