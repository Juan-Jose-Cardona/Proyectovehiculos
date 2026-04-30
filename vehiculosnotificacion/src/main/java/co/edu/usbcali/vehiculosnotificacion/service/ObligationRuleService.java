package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleResponse;

import java.util.List;

public interface ObligationRuleService {

    CreateObligationRuleResponse createObligationRule(CreateObligationRuleRequest createObligationRuleRequest) throws Exception;

    List<CreateObligationRuleResponse> getAllObligationRules();

    CreateObligationRuleResponse getObligationRuleById(Integer id) throws Exception;

    CreateObligationRuleResponse updateObligationRule(Integer id, UpdateObligationRuleRequest updateObligationRuleRequest) throws Exception;

    void deleteObligationRule(Integer id) throws Exception;

}
