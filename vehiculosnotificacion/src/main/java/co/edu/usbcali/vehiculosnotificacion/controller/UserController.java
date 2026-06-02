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

//importa el valid
import jakarta.validation.Valid;

//importa para agregar documentacion de swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Tag(name = "users", description = "operaciones de usuarios")
public class UserController {

    //inserciones de dependencias
    //private final UserRepository userRepository;

    private final UserService userService;

    @GetMapping("/ping")
    @Operation(summary = "verificar usuarios")
    public String ping(){
        return "pong";
    }

    @GetMapping("/all")
    @Operation(summary = "listar usuarios")
    public List<GetUserResponse> getAllUsers(){


        return userService.getAllUsers();


    }

    //busca por id
    @GetMapping("/{id}")
    @Operation(summary = "buscar usuario por id")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable Integer id){

        GetUserResponse userResponse = userService.getUserById(id);
        return new ResponseEntity<>(
                userResponse,
                HttpStatus.CREATED
        );

    }

    //crea user
    @PostMapping("/create")
    @Operation(summary = "crear usuario")
    public ResponseEntity<GetUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) throws Exception {

        GetUserResponse userCreated = userService.createUser(createUserRequest);

        return new ResponseEntity<>(
                userCreated,
                HttpStatus.CREATED
        );

    }

    //put del usuario por id
    @PutMapping("/update/{id}")
    @Operation(summary = "actualizar usuario")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserRequest updateUserRequest) throws Exception {

        //llama service update
        UpdateUserResponse userUpdated = userService.updateUser(id, updateUserRequest);

        //devuelve el response entity
        return new ResponseEntity<>(
                userUpdated,
                HttpStatus.CREATED
        );
    }

    //delete para borrar un usuario
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "eliminar usuario")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) throws Exception {

        //llama service delete
        userService.deleteUser(id);

        //retorna mensaje
        return new ResponseEntity<>(
                "Usuario eliminado correctamente",
                HttpStatus.OK
        );
    }


}
