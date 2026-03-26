package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.response.GetUserResponse;
import co.edu.usbcali.vehiculosnotificacion.mapper.UserMapper;
import co.edu.usbcali.vehiculosnotificacion.model.User;
import co.edu.usbcali.vehiculosnotificacion.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @GetMapping("/all")
    public List<GetUserResponse> getAllUsers(){

        //declara una nueva lista de userResponse
        List<GetUserResponse>  usersResponse;

        //ir al repository y obtener todos los usuarios
        List<User> users = userRepository.findAll();

        //convertir la lista de peliculas a lista de userResponse
        usersResponse = UserMapper.entityToListGetUserResponse(users);

        return usersResponse;

        //return userRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable Integer id){

        //buscar entidad por ID
        User user = userRepository.getReferenceById(id);

        // convertir a DTO response
        GetUserResponse userResponse = UserMapper.entityToGetUserResponse(user);

        //retorn ar DTO response
        return new ResponseEntity<>(

                userResponse,
                HttpStatus.OK

        );

        /*
        return new ResponseEntity<>(
                userRepository.getReferenceById(id),
                HttpStatus.OK
        );
        */

    }

}
