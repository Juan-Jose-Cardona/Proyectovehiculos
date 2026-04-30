package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateUserRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.GetUserResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateUserRequest;
import co.edu.usbcali.vehiculosnotificacion.mapper.UserMapper;
import co.edu.usbcali.vehiculosnotificacion.model.User;
import co.edu.usbcali.vehiculosnotificacion.repository.UserRepository;
import co.edu.usbcali.vehiculosnotificacion.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    //inserciones de dependencias
    //private final UserRepository userRepository;

    private final UserService userService;

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @GetMapping("/all")
    public List<GetUserResponse> getAllUsers(){


        return userService.getAllUsers();


    }


    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable Integer id){

        GetUserResponse userResponse = userService.getUserById(id);
        return new ResponseEntity<>(
                userResponse,
                HttpStatus.CREATED
        );

    }

    @PostMapping("/create")
    public ResponseEntity<GetUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) throws Exception {

        GetUserResponse userCreated = userService.createUser(createUserRequest);

        return new ResponseEntity<>(
                userCreated,
                HttpStatus.CREATED
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<GetUserResponse> updateUser(
            @PathVariable("id") Integer id,
            @RequestBody UpdateUserRequest updateUserRequest
    ) throws Exception {

        GetUserResponse userUpdated = userService.updateUser(id, updateUserRequest);

        return new ResponseEntity<>(
                userUpdated,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) throws Exception {

        userService.deleteUser(id);

        return new ResponseEntity<>(
                "Usuario eliminado correctamente",
                HttpStatus.OK
        );
    }

}
