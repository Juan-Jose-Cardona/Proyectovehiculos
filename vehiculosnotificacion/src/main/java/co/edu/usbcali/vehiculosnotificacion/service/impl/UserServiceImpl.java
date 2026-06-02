package co.edu.usbcali.vehiculosnotificacion.service.impl;

import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateUserRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.GetUserResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.request.UpdateUserRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateUserResponse;
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

    //Obtiene la lista de usuarios
    @Override
    public List<GetUserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<GetUserResponse> getUserResponseList = UserMapper.entityToListGetUserResponse(users);
        return getUserResponseList;
    }

    //obtiene un usuario segun id
    @Override
    public GetUserResponse getUserById(Integer id) {

        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));
        GetUserResponse getUserResponse = UserMapper.entityToGetUserResponse(user);
        return getUserResponse;
    }

    //metodo para hacer update
    @Override
    public UpdateUserResponse updateUser(Integer id, UpdateUserRequest updateUserRequest) throws Exception {

        try {

            // Validar id no nulo
            if (id == null){
                throw new Exception("El User vehicle debe existir");
            }

            //valida request no nulo
            if (updateUserRequest == null){
                throw new Exception("El objeto UpdateUserRequest no puede ser nulo");
            }

            //busca usuario por id
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with id; " + id));

            //actualiza email
            if (updateUserRequest.getEmail() != null) {
                user.setEmail(updateUserRequest.getEmail());
            }

            //actualiza telefono
            if (updateUserRequest.getPhone() != null) {
                user.setPhone(updateUserRequest.getPhone());
            }

            //actualiza contraseña
            if (updateUserRequest.getPassword() != null) {
                user.setPassword(updateUserRequest.getPassword());
            }

            //actualiza nombre completo
            if (updateUserRequest.getFullName() != null) {
                user.setFullName(updateUserRequest.getFullName());
            }

            //actualiza zona horaria
            if (updateUserRequest.getTimezone() != null) {
                user.setTimezone(updateUserRequest.getTimezone());
            }

            //actualiza estado activo
            if (updateUserRequest.getIsActive() != null) {
                user.setIsActive(updateUserRequest.getIsActive());
            }

            //guarda entidad actualizada
            user = userRepository.save(user);

            //convierte a update response
            UpdateUserResponse response = UserMapper.entityToUpdateUserResponse(user);

            //retorna dto
            return response;

        } catch (Exception e) {
            throw e;
        }
    }

    //metodo para eliminar usuario
    @Override
    public void deleteUser(Integer id) throws Exception {

        try {

            //valida id no nulo
            if (id == null){
                throw new Exception("El id del usuario es requerido");
            }

            //busca usuario por id
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("El ID:  " + id + " .No es valido"));

            //elimina usuario
            userRepository.delete(user);

        } catch (Exception e) {
            throw e;
        }
    }




}
