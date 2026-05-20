package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleResponse;

import java.util.List;

public interface ObligationRuleService {

    CreateObligationRuleResponse createObligationRule(CreateObligationRuleRequest createObligationRuleRequest) throws Exception;


    //get all
    List<CreateObligationRuleResponse> getAllObligationRules();


    //get by id
    CreateObligationRuleResponse getObligationRuleById(Integer id);

    //put
    UpdateObligationRuleResponse updateObligationRule(Integer id, UpdateObligationRuleRequest updateObligationRuleRequest) throws Exception;


}
