package co.edu.usbcali.vehiculosnotificacion.dto.request;


import co.edu.usbcali.vehiculosnotificacion.model.enums.AuditAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//valid
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuditLogRequest {

    //valida usuario requerido
    @NotNull(message = "El userId es requerido")
    @Positive(message = "El userId debe ser mayor a 0")
    private Integer userId;

    //valida entidad requerida
    @NotBlank(message = "El tipo de entidad es requerido")
    @Size(max = 100, message = "El tipo de entidad soporta hasta 100 caracteres")
    private String entityType;

    //valida entidad opcional
    @Positive(message = "El entityId debe ser mayor a 0")
    private Integer entityId;

    //valida accion requerida
    @NotNull(message = "La accion es requerida")
    private AuditAction action;

    private String beforeJson;
    private String afterJson;

    //valida ip opcional
    @Size(max = 45, message = "La ip soporta hasta 45 caracteres")
    private String ip;

    private String userAgent;

}
