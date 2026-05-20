package co.edu.usbcali.vehiculosnotificacion.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateUserResponse {

    //atributos a actualizar


    private Integer id;
    private String email;
    private String phone;
    private String password;
    private String fullName;
    private String timezone;
    private Boolean isActive;

}
