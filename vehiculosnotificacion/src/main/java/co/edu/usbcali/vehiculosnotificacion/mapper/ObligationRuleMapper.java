package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleResponse;
import co.edu.usbcali.vehiculosnotificacion.model.Obligation;
import co.edu.usbcali.vehiculosnotificacion.model.ObligationRule;

import java.util.List;
import java.util.Objects;

public class ObligationRuleMapper {

    public static CreateObligationRuleResponse entityToCreateObligationRuleResponse(ObligationRule obligationRule) {
        return CreateObligationRuleResponse.builder()
                .id(obligationRule.getId())
                .obligationId(
                        Objects.nonNull(obligationRule.getObligation())
                                ? obligationRule.getObligation().getId()
                                : null
                )
                .notifyDays(obligationRule.getNotifyDays())
                .channels(obligationRule.getChannels())
                .sendWindowStart(obligationRule.getSendWindowStart())
                .sendWindowEnd(obligationRule.getSendWindowEnd())
                .isEnabled(obligationRule.getIsEnabled())
                .createdAt(obligationRule.getCreatedAt())
                .updatedAt(obligationRule.getUpdatedAt())
                .build();
    }

    public static ObligationRule createObligationRuleRequestToEntity(
            CreateObligationRuleRequest createObligationRuleRequest,
            Obligation obligation
    ) {
        return ObligationRule.builder()
                .obligation(obligation)
                .notifyDays(createObligationRuleRequest.getNotifyDays())
                .channels(createObligationRuleRequest.getChannels())
                .sendWindowStart(createObligationRuleRequest.getSendWindowStart())
                .sendWindowEnd(createObligationRuleRequest.getSendWindowEnd())
                .isEnabled(createObligationRuleRequest.getIsEnabled())
                .build();
    }

    public static List<CreateObligationRuleResponse> entityToListCreateObligationRuleResponse(List<ObligationRule> obligationRules) {
        return obligationRules.stream()
                .map(ObligationRuleMapper::entityToCreateObligationRuleResponse)
                .toList();
    }

}
