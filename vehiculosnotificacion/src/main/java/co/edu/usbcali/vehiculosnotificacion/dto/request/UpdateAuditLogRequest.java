package co.edu.usbcali.vehiculosnotificacion.dto.request;

//importa la clase de AuditAction para que se reconozca el atributo AuditAction
import co.edu.usbcali.vehiculosnotificacion.model.enums.AuditAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//importa valids
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAuditLogRequest {

    //atributos a cambiar

    //valida usuario positivo
    @Positive(message = "El userId debe ser mayor a 0")
    private Integer userId;

    //valida entidad tamaño
    @Size(max = 100, message = "El entityType soporta hasta 100 caracteres")
    private String entityType;

    //valida entidad positiva
    @Positive(message = "El entityId debe ser mayor a 0")
    private Integer entityId;

    private AuditAction action;

    private String beforeJson;

    private String afterJson;

    //valida ip tamaño
    @Size(max = 45, message = "La ip soporta hasta 45 caracteres")
    private String ip;

    private String userAgent;

}
