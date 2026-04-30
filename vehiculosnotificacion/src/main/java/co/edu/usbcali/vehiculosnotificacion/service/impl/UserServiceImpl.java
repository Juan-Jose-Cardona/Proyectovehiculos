package co.edu.usbcali.vehiculosnotificacion.service.impl;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateUserRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.GetUserResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateUserRequest;
import co.edu.usbcali.vehiculosnotificacion.mapper.UserMapper;
import co.edu.usbcali.vehiculosnotificacion.model.User;
import co.edu.usbcali.vehiculosnotificacion.repository.UserRepository;
import co.edu.usbcali.vehiculosnotificacion.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    //Inyeccion de dependencias (repository)
    private final UserRepository userRepository;
    private final EntityManagerFactoryInfo entityManagerFactoryInfo;

    @Override
    public GetUserResponse createUser(CreateUserRequest createUserRequest) throws Exception {

        try {

            //Validar el objeto createUserRequest y sus atributos
            if (createUserRequest == null){
                throw new Exception("El objeto CreateUserRequest no puede ser nulo");
            }

            if (createUserRequest.getEmail() == null || createUserRequest.getEmail().isBlank()){
                throw new Exception("El email es requerido");
            }

            if (createUserRequest.getEmail().length() > 255){
                throw new Exception("El email soporta hasta 255 caracteres");
            }

            if (createUserRequest.getPhone() == null || createUserRequest.getPhone().isBlank()){
                throw new Exception("El telefono es requerido");
            }

            if (createUserRequest.getPassword() == null || createUserRequest.getPassword().isBlank()){
                throw new Exception("La contraseña es requerida");
            }

            //convertir desde el request hacia la entidad usando el mapper
            User user = UserMapper.createUserRequestToEntity(createUserRequest);

            //guardar el user (entidad) usando el repository
            user = userRepository.save(user);

            //mapear la entidad a DTO y retornar
            GetUserResponse getUserResponse = UserMapper.entityToGetUserResponse(user);
            //retorna el DTO
            return getUserResponse;

        } catch (Exception e) {
            throw e;
        }


    }

    @Override
    public List<GetUserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<GetUserResponse> getUserResponseList = UserMapper.entityToListGetUserResponse(users);
        return getUserResponseList;
    }

    @Override
    public GetUserResponse getUserById(Integer id) {

        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found with id; " + id));
        GetUserResponse getUserResponse = UserMapper.entityToGetUserResponse(user);
        return getUserResponse;
    }

    @Override
    public GetUserResponse updateUser(Integer id, UpdateUserRequest updateUserRequest) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        if (updateUserRequest == null) {
            throw new Exception("El objeto UpdateUserRequest no puede ser nulo");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el user con id " + id));

        if (updateUserRequest.getEmail() != null) {
            if (updateUserRequest.getEmail().isBlank()) {
                throw new Exception("El email no puede estar vacio");
            }

            if (updateUserRequest.getEmail().length() > 255) {
                throw new Exception("El email soporta hasta 255 caracteres");
            }

            user.setEmail(updateUserRequest.getEmail());
        }

        if (updateUserRequest.getPhone() != null) {
            if (updateUserRequest.getPhone().isBlank()) {
                throw new Exception("El telefono no puede estar vacio");
            }

            if (updateUserRequest.getPhone().length() > 20) {
                throw new Exception("El telefono soporta hasta 20 caracteres");
            }

            user.setPhone(updateUserRequest.getPhone());
        }

        if (updateUserRequest.getPassword() != null) {
            if (updateUserRequest.getPassword().isBlank()) {
                throw new Exception("La contraseña no puede estar vacia");
            }

            user.setPassword(updateUserRequest.getPassword());
        }

        if (updateUserRequest.getFullName() != null) {
            if (updateUserRequest.getFullName().length() > 255) {
                throw new Exception("El nombre completo soporta hasta 255 caracteres");
            }

            user.setFullName(updateUserRequest.getFullName());
        }

        if (updateUserRequest.getTimezone() != null) {
            if (updateUserRequest.getTimezone().isBlank()) {
                throw new Exception("La zona horaria no puede estar vacia");
            }

            if (updateUserRequest.getTimezone().length() > 100) {
                throw new Exception("La zona horaria soporta hasta 100 caracteres");
            }

            user.setTimezone(updateUserRequest.getTimezone());
        }

        if (updateUserRequest.getIsActive() != null) {
            user.setIsActive(updateUserRequest.getIsActive());
        }

        user = userRepository.save(user);

        return UserMapper.entityToGetUserResponse(user);
    }

    @Override
    public void deleteUser(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El id es requerido");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el user con id " + id));

        userRepository.delete(user);
    }


}
