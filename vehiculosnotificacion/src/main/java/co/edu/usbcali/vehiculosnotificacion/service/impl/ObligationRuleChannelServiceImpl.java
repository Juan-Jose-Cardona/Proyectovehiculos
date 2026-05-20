package co.edu.usbcali.vehiculosnotificacion.service.impl;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleChannelRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateObligationRuleChannelRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleChannelResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleChannelResponse;
import co.edu.usbcali.vehiculosnotificacion.mapper.ObligationRuleChannelMapper;
import co.edu.usbcali.vehiculosnotificacion.model.ObligationRule;
import co.edu.usbcali.vehiculosnotificacion.model.ObligationRuleChannel;
import co.edu.usbcali.vehiculosnotificacion.repository.ObligationRuleChannelRepository;
import co.edu.usbcali.vehiculosnotificacion.repository.ObligationRuleRepository;
import co.edu.usbcali.vehiculosnotificacion.service.ObligationRuleChannelService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ObligationRuleChannelServiceImpl implements ObligationRuleChannelService {


    private final ObligationRuleChannelRepository obligationRuleChannelRepository;
    private final ObligationRuleRepository obligationRuleRepository;

    //crea obligation rule channel
    @Override
    public CreateObligationRuleChannelResponse createObligationRuleChannel(
            CreateObligationRuleChannelRequest createObligationRuleChannelRequest
    ) throws Exception {

        try {

            //valida request no nulo
            if (createObligationRuleChannelRequest == null) {
                throw new Exception("El objeto CreateObligationRuleChannelRequest no puede ser nulo");
            }

            //valida obligation rule id
            if (createObligationRuleChannelRequest.getObligationRuleId() == null
                    || createObligationRuleChannelRequest.getObligationRuleId() <= 0) {
                throw new Exception("El obligationRuleId es requerido");
            }

            //valida canal requerido
            if (createObligationRuleChannelRequest.getChannel() == null) {
                throw new Exception("El canal es requerido");
            }

            //busca obligation rule por id
            ObligationRule obligationRule = obligationRuleRepository.findById(
                            createObligationRuleChannelRequest.getObligationRuleId()
                    )
                    .orElseThrow(() -> new Exception(
                            "No se encontro la obligation rule con id "
                                    + createObligationRuleChannelRequest.getObligationRuleId()
                    ));

            //convierte request a entidad
            ObligationRuleChannel obligationRuleChannel =
                    ObligationRuleChannelMapper.createObligationRuleChannelRequestToEntity(
                            createObligationRuleChannelRequest,
                            obligationRule
                    );

            //guarda entidad
            obligationRuleChannel = obligationRuleChannelRepository.save(obligationRuleChannel);

            //retorna response
            return ObligationRuleChannelMapper.entityToCreateObligationRuleChannelResponse(obligationRuleChannel);

        } catch (Exception e) {
            throw e;
        }
    }

    //obtiene lista
    @Override
    public List<CreateObligationRuleChannelResponse> getAllObligationRuleChannels() {

        List<ObligationRuleChannel> obligationRuleChannels = obligationRuleChannelRepository.findAll();
        List<CreateObligationRuleChannelResponse> createObligationRuleChannelResponseList =
                ObligationRuleChannelMapper.entityToListCreateObligationRuleChannelResponse(obligationRuleChannels);
        return createObligationRuleChannelResponseList;
    }

    //obtiene segun id
    @Override
    public CreateObligationRuleChannelResponse getObligationRuleChannelById(Integer id) {

        ObligationRuleChannel obligationRuleChannel = obligationRuleChannelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ObligationRuleChannel not found with id; " + id));

        CreateObligationRuleChannelResponse createObligationRuleChannelResponse =
                ObligationRuleChannelMapper.entityToCreateObligationRuleChannelResponse(obligationRuleChannel);
        return createObligationRuleChannelResponse;
    }

    //metodo para actualizar atributos
    @Override
    public UpdateObligationRuleChannelResponse updateObligationRuleChannel(
            Integer id,
            UpdateObligationRuleChannelRequest updateObligationRuleChannelRequest
    ) throws Exception {

        try {

            // Validar id no nulo
            if (id == null){
                throw new Exception("El objeto ObligationRuleChannel debe existir");
            }

            //valida request no nulo
            if (updateObligationRuleChannelRequest == null) {
                throw new Exception("El objeto UpdateObligationRuleChannelRequest no puede ser nulo");
            }

            //busca obligation rule channel
            ObligationRuleChannel obligationRuleChannel = obligationRuleChannelRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ObligationRuleChannel not found with id; " + id));

            //actualiza obligation rule
            if (updateObligationRuleChannelRequest.getObligationRuleId() != null) {
                ObligationRule obligationRule = obligationRuleRepository.findById(
                                updateObligationRuleChannelRequest.getObligationRuleId()
                        )
                        .orElseThrow(() -> new Exception(
                                "No se encontro la obligation rule con id "
                                        + updateObligationRuleChannelRequest.getObligationRuleId()
                        ));

                obligationRuleChannel.setObligationRule(obligationRule);
            }

            //actualiza canal
            if (updateObligationRuleChannelRequest.getChannel() != null) {
                obligationRuleChannel.setChannel(updateObligationRuleChannelRequest.getChannel());
            }

            //guarda entidad actualizada
            obligationRuleChannel = obligationRuleChannelRepository.save(obligationRuleChannel);

            //convierte a update response
            UpdateObligationRuleChannelResponse response =
                    ObligationRuleChannelMapper.entityToUpdateObligationRuleChannelResponse(obligationRuleChannel);

            //retorna dto
            return response;

        } catch (Exception e) {
            throw e;
        }
    }


}
