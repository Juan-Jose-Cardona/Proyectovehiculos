package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleResponse;
import co.edu.usbcali.vehiculosnotificacion.model.Obligation;
import co.edu.usbcali.vehiculosnotificacion.model.ObligationRule;
import java.util.List;
import java.util.Objects;

public class ObligationRuleMapper {

    //convierte entidad a response
    public static CreateObligationRuleResponse entityToCreateObligationRuleResponse(ObligationRule obligationRule) {

        //instanciar nuevo objeto
        CreateObligationRuleResponse response = CreateObligationRuleResponse.builder()
                .id(obligationRule.getId())
                .obligationId(Objects.nonNull(obligationRule.getObligation()) ? obligationRule.getObligation().getId() : null)
                //.notifyDays(obligationRule.getNotifyDays())
                //.channels(obligationRule.getChannels())
                .sendWindowStart(obligationRule.getSendWindowStart())
                .sendWindowEnd(obligationRule.getSendWindowEnd())
                .isEnabled(obligationRule.getIsEnabled())
                .createdAt(obligationRule.getCreatedAt())
                .updatedAt(obligationRule.getUpdatedAt())
                .build();

        return response;
    }

    //convierte lista entidades a response
    public static List<CreateObligationRuleResponse> entityToListCreateObligationRuleResponse(List<ObligationRule> obligationRules) {

        //mapea lista usando stream
        return obligationRules.stream().map(ObligationRuleMapper::entityToCreateObligationRuleResponse).toList();
    }

    //convierte request a entidad
    public static ObligationRule createObligationRuleRequestToEntity(CreateObligationRuleRequest createObligationRuleRequest, Obligation obligation) {

        //construye entidad desde request
        return ObligationRule.builder()
                .obligation(obligation)
                //.notifyDays(createObligationRuleRequest.getNotifyDays())
                //.channels(createObligationRuleRequest.getChannels())
                .sendWindowStart(createObligationRuleRequest.getSendWindowStart())
                .sendWindowEnd(createObligationRuleRequest.getSendWindowEnd())
                .isEnabled(createObligationRuleRequest.getIsEnabled())
                .build();
    }


    //convierte entidad a update response
    public static UpdateObligationRuleResponse entityToUpdateObligationRuleResponse(ObligationRule obligationRule) {

        //instanciar nuevo objeto response
        UpdateObligationRuleResponse response = UpdateObligationRuleResponse.builder()
                .id(obligationRule.getId())
                .obligationId(Objects.nonNull(obligationRule.getObligation()) ? obligationRule.getObligation().getId() : null)
                //.notifyDays(obligationRule.getNotifyDays())
                //.channels(obligationRule.getChannels())
                .sendWindowStart(obligationRule.getSendWindowStart())
                .sendWindowEnd(obligationRule.getSendWindowEnd())
                .isEnabled(obligationRule.getIsEnabled())
                .createdAt(obligationRule.getCreatedAt())
                .updatedAt(obligationRule.getUpdatedAt())
                .build();

        //retorna response
        return response;
    }

}
