package co.edu.usbcali.vehiculosnotificacion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//valid
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    //valida formato email
    @NotBlank(message = "El email es requerido")
    @Email(message = "El email no tiene formato valido")
    @Size(max = 255, message = "El email soporta hasta 255 caracteres")
    private String email;

    //valida telefono requerido
    @NotBlank(message = "El telefono es requerido")
    @Size(max = 20, message = "El telefono soporta hasta 20 caracteres")
    private String phone;

    //valida contraseña requerida
    @NotBlank(message = "La contraseña es requerida")
    private String password;

    //valida nombre opcional
    @Size(max = 255, message = "El nombre soporta hasta 255 caracteres")
    private String fullName;

    //valida zona horaria opcional
    @Size(max = 100, message = "La zona horaria soporta hasta 100 caracteres")
    private String timezone;

    private Boolean isActive;


}
