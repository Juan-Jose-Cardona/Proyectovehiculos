package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleChannelRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleChannelResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleChannelResponse;
import co.edu.usbcali.vehiculosnotificacion.model.ObligationRule;
import co.edu.usbcali.vehiculosnotificacion.model.ObligationRuleChannel;

import java.util.List;

public class ObligationRuleChannelMapper {

    //convierte entidad a response
    public static CreateObligationRuleChannelResponse entityToCreateObligationRuleChannelResponse(
            ObligationRuleChannel obligationRuleChannel
    ) {

        //instanciar nuevo objeto response
        CreateObligationRuleChannelResponse response = CreateObligationRuleChannelResponse.builder()
                .id(obligationRuleChannel.getId())
                .obligationRuleId(obligationRuleChannel.getObligationRule().getId())
                .channel(obligationRuleChannel.getChannel())
                .build();

        //retorna response
        return response;
    }

    //convierte lista a response
    public static List<CreateObligationRuleChannelResponse> entityToListCreateObligationRuleChannelResponse(
            List<ObligationRuleChannel> obligationRuleChannels
    ) {

        //retorna lista response
        return obligationRuleChannels.stream()
                .map(ObligationRuleChannelMapper::entityToCreateObligationRuleChannelResponse)
                .toList();
    }

    //convierte request a entidad
    public static ObligationRuleChannel createObligationRuleChannelRequestToEntity(
            CreateObligationRuleChannelRequest createObligationRuleChannelRequest,
            ObligationRule obligationRule
    ) {

        //instanciar nueva entidad
        ObligationRuleChannel obligationRuleChannel = ObligationRuleChannel.builder()
                .obligationRule(obligationRule)
                .channel(createObligationRuleChannelRequest.getChannel())
                .build();

        //retorna entidad
        return obligationRuleChannel;
    }

    //convierte entidad a update response
    public static UpdateObligationRuleChannelResponse entityToUpdateObligationRuleChannelResponse(
            ObligationRuleChannel obligationRuleChannel
    ) {

        //instanciar nuevo objeto response
        UpdateObligationRuleChannelResponse response = UpdateObligationRuleChannelResponse.builder()
                .id(obligationRuleChannel.getId())
                .obligationRuleId(obligationRuleChannel.getObligationRule().getId())
                .channel(obligationRuleChannel.getChannel())
                .build();

        //retorna response
        return response;
    }


}
