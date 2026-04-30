package co.edu.usbcali.vehiculosnotificacion.service.impl;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleResponse;
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

    @Override
    public CreateObligationRuleResponse createObligationRule(CreateObligationRuleRequest request) throws Exception {

        if (request == null) {
            throw new Exception("El objeto CreateObligationRuleRequest no puede ser nulo");
        }

        if (request.getObligationId() == null || request.getObligationId() <= 0) {
            throw new Exception("El obligationId es requerido");
        }

        Obligation obligation = obligationRepository.findById(request.getObligationId())
                .orElseThrow(() -> new Exception("No se encontro la obligation con id " + request.getObligationId()));

        ObligationRule obligationRule = ObligationRuleMapper.createObligationRuleRequestToEntity(
                request,
                obligation
        );

        obligationRule = obligationRuleRepository.save(obligationRule);

        return ObligationRuleMapper.entityToCreateObligationRuleResponse(obligationRule);
    }

    @Override
    public List<CreateObligationRuleResponse> getAllObligationRules() {
        List<ObligationRule> rules = obligationRuleRepository.findAll();
        return ObligationRuleMapper.entityToListCreateObligationRuleResponse(rules);
    }

    @Override
    public CreateObligationRuleResponse getObligationRuleById(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        ObligationRule rule = obligationRuleRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro la obligationRule con id " + id));

        return ObligationRuleMapper.entityToCreateObligationRuleResponse(rule);
    }

    @Override
    public CreateObligationRuleResponse updateObligationRule(Integer id, UpdateObligationRuleRequest request) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        if (request == null) {
            throw new Exception("El objeto UpdateObligationRuleRequest no puede ser nulo");
        }

        ObligationRule rule = obligationRuleRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro la obligationRule con id " + id));

        if (request.getObligationId() != null) {
            Obligation obligation = obligationRepository.findById(request.getObligationId())
                    .orElseThrow(() -> new Exception("No se encontro la obligation con id " + request.getObligationId()));
            rule.setObligation(obligation);
        }

        if (request.getNotifyDays() != null) rule.setNotifyDays(request.getNotifyDays());
        if (request.getChannels() != null) rule.setChannels(request.getChannels());
        if (request.getSendWindowStart() != null) rule.setSendWindowStart(request.getSendWindowStart());
        if (request.getSendWindowEnd() != null) rule.setSendWindowEnd(request.getSendWindowEnd());
        if (request.getIsEnabled() != null) rule.setIsEnabled(request.getIsEnabled());

        rule = obligationRuleRepository.save(rule);

        return ObligationRuleMapper.entityToCreateObligationRuleResponse(rule);
    }

    @Override
    public void deleteObligationRule(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        ObligationRule rule = obligationRuleRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro la obligationRule con id " + id));

        obligationRuleRepository.delete(rule);
    }
}