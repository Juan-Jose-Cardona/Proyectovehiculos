package co.edu.usbcali.vehiculosnotificacion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    //configura informacion swagger
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("vehiculos notificacion api")
                .version("1.0")
                .description("api para gestionar vehiculos, obligaciones y notificaciones"));
    }


}
