package co.edu.usbcali.vehiculosnotificacion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//imports de valid
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    //atributos que se van a actualizar

    //valida email formato
    @Email(message = "El email no tiene formato valido")
    @Size(max = 255, message = "El email soporta hasta 255 caracteres")
    private String email;

    //valida telefono tamaño
    @Size(max = 20, message = "El telefono soporta hasta 20 caracteres")
    private String phone;

    //valida contraseña tamaño
    @Size(max = 500, message = "La contraseña soporta hasta 500 caracteres")
    private String password;

    //valida nombre tamaño
    @Size(max = 255, message = "El nombre soporta hasta 255 caracteres")
    private String fullName;

    //valida timezone tamaño
    @Size(max = 100, message = "La timezone soporta hasta 100 caracteres")
    private String timezone;

    private Boolean isActive;

}