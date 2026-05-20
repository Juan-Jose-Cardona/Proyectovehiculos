package co.edu.usbcali.vehiculosnotificacion.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    //estructura de las excepciones para que salgan errores cortos

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
