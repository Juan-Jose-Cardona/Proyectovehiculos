package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationResponse;

import java.util.List;


public interface ObligationService {

    CreateObligationResponse createObligation(CreateObligationRequest createObligationRequest) throws Exception;

    List<CreateObligationResponse> getAllObligations();

    CreateObligationResponse getObligationById(Integer id) throws Exception;

    CreateObligationResponse updateObligation(Integer id, UpdateObligationRequest updateObligationRequest) throws Exception;

    void deleteObligation(Integer id) throws Exception;

}
