package co.edu.usbcali.vehiculosnotificacion.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserResponse {

    //lo ideal es que esta clase sea inmutable

    private Integer id;
    private String email;
    private String phone;
    private String password;
    private String fullName;
    private String timezone;
    private Boolean isActive;

}
