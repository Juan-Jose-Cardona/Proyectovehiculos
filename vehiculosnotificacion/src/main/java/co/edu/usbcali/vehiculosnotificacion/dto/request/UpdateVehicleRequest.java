package co.edu.usbcali.vehiculosnotificacion.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//valids
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleRequest {

    //atributos que se van a actualizar

    //valida usuario positivo
    @Positive(message = "El userId debe ser mayor a 0")
    private Integer userId;

    //valida placa tamaño
    @Size(max = 20, message = "La placa soporta hasta 20 caracteres")
    private String plate;

    //valida marca tamaño
    @Size(max = 100, message = "La marca soporta hasta 100 caracteres")
    private String brand;

    //valida linea tamaño
    @Size(max = 100, message = "La linea soporta hasta 100 caracteres")
    private String lineModel;

    //valida año minimo
    @Min(value = 1900, message = "El año debe ser mayor o igual a 1900")
    @Max(value = 2100, message = "El año debe ser menor o igual a 2100")
    private Integer modelYear;

    //valida tipo tamaño
    @Size(max = 50, message = "El tipo de vehiculo soporta hasta 50 caracteres")
    private String vehicleType;

    //valida notas tamaño
    @Size(max = 2000, message = "Las notas soportan hasta 2000 caracteres")
    private String notes;

}
