package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleNotifyDayRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleNotifyDayRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleNotifyDayResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleNotifyDayResponse;

import java.util.List;

public interface ObligationRuleNotifyDayService {

    //create
    CreateObligationRuleNotifyDayResponse createObligationRuleNotifyDay(CreateObligationRuleNotifyDayRequest createObligationRuleNotifyDayRequest) throws Exception;

    //get all
    List<CreateObligationRuleNotifyDayResponse> getAllObligationRuleNotifyDays();

    //get by id
    CreateObligationRuleNotifyDayResponse getObligationRuleNotifyDayById(Integer id);

    //update
    UpdateObligationRuleNotifyDayResponse updateObligationRuleNotifyDay(Integer id, UpdateObligationRuleNotifyDayRequest updateObligationRuleNotifyDayRequest) throws Exception;

    //delete
    void deleteObligationRuleNotifyDay(Integer id) throws Exception;

}
