package co.edu.usbcali.vehiculosnotificacion.mapper;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateObligationRuleNotifyDayRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.CreateObligationRuleNotifyDayResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateObligationRuleNotifyDayResponse;
import co.edu.usbcali.vehiculosnotificacion.model.ObligationRule;
import co.edu.usbcali.vehiculosnotificacion.model.ObligationRuleNotifyDay;

import java.util.List;

public class ObligationRuleNotifyDayMapper {

    //convierte entidad a response
    public static CreateObligationRuleNotifyDayResponse entityToCreateObligationRuleNotifyDayResponse(
            ObligationRuleNotifyDay obligationRuleNotifyDay
    ) {

        //instanciar nuevo objeto response
        CreateObligationRuleNotifyDayResponse response = CreateObligationRuleNotifyDayResponse.builder()
                .id(obligationRuleNotifyDay.getId())
                .obligationRuleId(obligationRuleNotifyDay.getObligationRule().getId())
                .notifyDay(obligationRuleNotifyDay.getNotifyDay())
                .build();

        //retorna response
        return response;
    }

    //convierte lista a response
    public static List<CreateObligationRuleNotifyDayResponse> entityToListCreateObligationRuleNotifyDayResponse(
            List<ObligationRuleNotifyDay> obligationRuleNotifyDays
    ) {

        //retorna lista response
        return obligationRuleNotifyDays.stream()
                .map(ObligationRuleNotifyDayMapper::entityToCreateObligationRuleNotifyDayResponse)
                .toList();
    }

    //convierte request a entidad
    public static ObligationRuleNotifyDay createObligationRuleNotifyDayRequestToEntity(
            CreateObligationRuleNotifyDayRequest createObligationRuleNotifyDayRequest,
            ObligationRule obligationRule
    ) {

        //instanciar nueva entidad
        ObligationRuleNotifyDay obligationRuleNotifyDay = ObligationRuleNotifyDay.builder()
                .obligationRule(obligationRule)
                .notifyDay(createObligationRuleNotifyDayRequest.getNotifyDay())
                .build();

        //retorna entidad
        return obligationRuleNotifyDay;
    }

    //convierte entidad a update response
    public static UpdateObligationRuleNotifyDayResponse entityToUpdateObligationRuleNotifyDayResponse(
            ObligationRuleNotifyDay obligationRuleNotifyDay
    ) {

        //instanciar nuevo objeto response
        UpdateObligationRuleNotifyDayResponse response = UpdateObligationRuleNotifyDayResponse.builder()
                .id(obligationRuleNotifyDay.getId())
                .obligationRuleId(obligationRuleNotifyDay.getObligationRule().getId())
                .notifyDay(obligationRuleNotifyDay.getNotifyDay())
                .build();

        //retorna response
        return response;
    }


}
