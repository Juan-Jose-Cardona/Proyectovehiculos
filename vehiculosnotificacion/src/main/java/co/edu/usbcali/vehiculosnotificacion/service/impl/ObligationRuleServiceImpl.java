package co.edu.usbcali.vehiculosnotificacion.service.impl;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleResponse;
import co.edu.usbcali.vehiculosnotificacion.mapper.ObligationRuleMapper;
import co.edu.usbcali.vehiculosnotificacion.model.Obligation;
import co.edu.usbcali.vehiculosnotificacion.model.ObligationRule;
import co.edu.usbcali.vehiculosnotificacion.repository.ObligationRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.ObligationRuleRepository;
import co.edu.usbcali.vehiculosnotificacion.service.ObligationRuleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ObligationRuleServiceImpl implements ObligationRuleService {

    private final ObligationRuleRepository obligationRuleRepository;
    private final ObligationRepository obligationRepository;

    //crea obligation rule
    @Override
    public CreateObligationRuleResponse createObligationRule(CreateObligationRuleRequest createObligationRuleRequest) throws Exception {

        try {

            //valida request no nulo
            if (createObligationRuleRequest == null) {
                throw new Exception("El objeto CreateObligationRuleRequest no puede ser nulo");
            }

            //valida obligation id
            if (createObligationRuleRequest.getObligationId() == null || createObligationRuleRequest.getObligationId() <= 0) {
                throw new Exception("El obligationId es requerido");
            }

            //busca obligacion por id
            Obligation obligation = obligationRepository.findById(createObligationRuleRequest.getObligationId())
                    .orElseThrow(() -> new Exception(
                            "No se encontro la obligation con id " + createObligationRuleRequest.getObligationId()
                    ));

            //convierte request a entidad
            ObligationRule obligationRule = ObligationRuleMapper.createObligationRuleRequestToEntity(
                    createObligationRuleRequest,
                    obligation
            );

            //guarda entidad
            obligationRule = obligationRuleRepository.save(obligationRule);

            //retorna response
            return ObligationRuleMapper.entityToCreateObligationRuleResponse(obligationRule);

        } catch (Exception e) {
            throw e;
        }
    }

    //obtiene lista obligation rules
    @Override
    public List<CreateObligationRuleResponse> getAllObligationRules() {

        List<ObligationRule> obligationRules = obligationRuleRepository.findAll();
        List<CreateObligationRuleResponse> createObligationRuleResponseList = ObligationRuleMapper.entityToListCreateObligationRuleResponse(obligationRules);
        return createObligationRuleResponseList;
    }

    //obtiene obligation rule segun id
    @Override
    public CreateObligationRuleResponse getObligationRuleById(Integer id) {

        ObligationRule obligationRule = obligationRuleRepository.findById(id).
                orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));

        CreateObligationRuleResponse createObligationRuleResponse = ObligationRuleMapper.entityToCreateObligationRuleResponse(obligationRule);
        return createObligationRuleResponse;
    }

    //metodo para actualizar atributos
    @Override
    public UpdateObligationRuleResponse updateObligationRule(Integer id, UpdateObligationRuleRequest updateObligationRuleRequest) throws Exception {

        try {

            // Validar id no nulo
            if (id == null){
                throw new Exception("El objeto Obligatio Rule debe existir");
            }

            //valida request no nulo
            if (updateObligationRuleRequest == null) {
                throw new Exception("El objeto UpdateObligationRuleRequest no puede ser nulo");
            }

            //busca obligation rule por id
            ObligationRule obligationRule = obligationRuleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));

            //actualiza obligacion rule
            if (updateObligationRuleRequest.getObligationId() != null) {
                Obligation obligation = obligationRepository.findById(updateObligationRuleRequest.getObligationId())
                        .orElseThrow(() -> new Exception(
                                "No se encontro la obligation con id " + updateObligationRuleRequest.getObligationId()
                        ));

                obligationRule.setObligation(obligation);
            }

            /*
            //actualiza dias notificacion
            if (updateObligationRuleRequest.getNotifyDays() != null) {
                obligationRule.setNotifyDays(updateObligationRuleRequest.getNotifyDays());
            }

            //actualiza canales
            if (updateObligationRuleRequest.getChannels() != null) {
                obligationRule.setChannels(updateObligationRuleRequest.getChannels());
            }
            */

            //actualiza ventana inicio
            if (updateObligationRuleRequest.getSendWindowStart() != null) {
                obligationRule.setSendWindowStart(updateObligationRuleRequest.getSendWindowStart());
            }

            //actualiza ventana fin
            if (updateObligationRuleRequest.getSendWindowEnd() != null) {
                obligationRule.setSendWindowEnd(updateObligationRuleRequest.getSendWindowEnd());
            }

            //actualiza estado habilitado
            if (updateObligationRuleRequest.getIsEnabled() != null) {
                obligationRule.setIsEnabled(updateObligationRuleRequest.getIsEnabled());
            }

            //guarda entidad actualizada
            obligationRule = obligationRuleRepository.save(obligationRule);

            //convierte a response dto
            UpdateObligationRuleResponse response = ObligationRuleMapper.entityToUpdateObligationRuleResponse(obligationRule);

            //retorna dto
            return response;

        } catch (Exception e) {
            throw e;
        }
    }


    //metodo para eliminar obligationRule
    @Override
    public void deleteObligationRule(Integer id) throws Exception {

        try {

            //valida id no nulo
            if (id == null){
                throw new Exception("El id del obligationRule es requerido");
            }

            //busca usuario por id
            ObligationRule obligationRule = obligationRuleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));

            //elimina usuario
            obligationRuleRepository.delete(obligationRule);

        } catch (Exception e) {
            throw e;
        }
    }

}
