package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleChannelRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleChannelRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleChannelResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleChannelResponse;

import java.util.List;

public interface ObligationRuleChannelService {

    //create
    CreateObligationRuleChannelResponse createObligationRuleChannel(CreateObligationRuleChannelRequest createObligationRuleChannelRequest) throws Exception;

    //get all
    List<CreateObligationRuleChannelResponse> getAllObligationRuleChannels();

    //get by id
    CreateObligationRuleChannelResponse getObligationRuleChannelById(Integer id);

    //put
    UpdateObligationRuleChannelResponse updateObligationRuleChannel(Integer id, UpdateObligationRuleChannelRequest updateObligationRuleChannelRequest) throws Exception;

    //delete
    void deleteObligationRuleChannel(Integer id) throws Exception;

}
