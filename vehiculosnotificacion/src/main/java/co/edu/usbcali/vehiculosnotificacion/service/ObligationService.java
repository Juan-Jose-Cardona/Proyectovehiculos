package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationResponse;

import java.util.List;

public interface ObligationService {

    CreateObligationResponse createObligation(CreateObligationRequest createObligationRequest) throws Exception;

    //get all
    List<CreateObligationResponse> getAllObligations();

    //get by id
    CreateObligationResponse getObligationById(Integer id);

    //put
    UpdateObligationResponse updateObligation(Integer id, UpdateObligationRequest updateObligationRequest) throws Exception;

}
