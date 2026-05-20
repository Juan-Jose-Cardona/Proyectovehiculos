package co.edu.usbcali.vehiculosnotificacion.controller;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateUserRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.GetUserResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateUserRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateUserResponse;
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

    //crea user
    @PostMapping("/create")
    public ResponseEntity<GetUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) throws Exception {

        GetUserResponse userCreated = userService.createUser(createUserRequest);

        return new ResponseEntity<>(
                userCreated,
                HttpStatus.CREATED
        );

    }

    //put del usuario por id
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Integer id, @RequestBody UpdateUserRequest updateUserRequest) throws Exception {

        //llama service update
        UpdateUserResponse userUpdated = userService.updateUser(id, updateUserRequest);

        //devuelve el response entity
        return new ResponseEntity<>(
                userUpdated,
                HttpStatus.CREATED
        );
    }



}
