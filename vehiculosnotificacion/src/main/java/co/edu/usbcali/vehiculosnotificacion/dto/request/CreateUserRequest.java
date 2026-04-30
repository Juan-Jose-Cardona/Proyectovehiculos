package co.edu.usbcali.vehiculosnotificacion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String email;
    private String phone;
    private String password;
    private String fullName;
    private String timezone;
    private Boolean isActive;


}
