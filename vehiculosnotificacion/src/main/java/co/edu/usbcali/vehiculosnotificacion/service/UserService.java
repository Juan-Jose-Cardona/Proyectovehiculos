package co.edu.usbcali.vehiculosnotificacion.service;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateUserRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.GetUserResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateUserRequest;

import java.util.List;

public interface UserService {

    GetUserResponse createUser(CreateUserRequest createUserRequest) throws Exception;
    List<GetUserResponse> getAllUsers();
    GetUserResponse getUserById(Integer id);

    GetUserResponse updateUser(Integer id, UpdateUserRequest updateUserRequest) throws Exception;

    void deleteUser(Integer id) throws Exception;

}
