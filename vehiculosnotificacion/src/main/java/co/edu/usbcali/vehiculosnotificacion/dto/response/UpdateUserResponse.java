package co.edu.usbcali.vehiculosnotificacion.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateUserResponse {

    private Integer id;
    private String email;

}
