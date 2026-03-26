package co.edu.usbcali.vehiculosnotificacion.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserResponse {

    //lo ideal es que esta clase sea inmutable

    private Integer id;
    private String title;

}
