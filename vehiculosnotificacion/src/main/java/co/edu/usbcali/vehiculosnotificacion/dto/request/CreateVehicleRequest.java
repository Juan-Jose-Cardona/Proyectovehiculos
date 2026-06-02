package co.edu.usbcali.vehiculosnotificacion.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

//validaciones
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleRequest {

    //valida usuario requerido
    @NotNull(message = "El userId es requerido")
    @Positive(message = "El userId debe ser mayor a 0")
    private Integer userId;

    //valida placa requerida
    @NotBlank(message = "La placa es requerida")
    @Size(max = 20, message = "La placa soporta hasta 20 caracteres")
    private String plate;

    //valida marca opcional
    @Size(max = 100, message = "La marca soporta hasta 100 caracteres")
    private String brand;

    //valida linea opcional
    @Size(max = 100, message = "La linea soporta hasta 100 caracteres")
    private String lineModel;

    //valida año opcional
    @Min(value = 1900, message = "El año debe ser mayor o igual a 1900")
    @Max(value = 2100, message = "El año debe ser menor o igual a 2100")
    private Integer modelYear;

    //valida tipo requerido
    @NotBlank(message = "El tipo de vehiculo es requerido")
    @Size(max = 50, message = "El tipo de vehiculo soporta hasta 50 caracteres")
    private String vehicleType;

    //valida notas opcionales
    @Size(max = 2000, message = "Las notas soportan hasta 2000 caracteres")
    private String notes;

    private Timestamp createdAt;
    private Timestamp updatedAt;


}
