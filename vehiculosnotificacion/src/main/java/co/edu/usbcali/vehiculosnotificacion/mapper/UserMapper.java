package co.edu.usbcali.vehiculosnotificacion.mapper;


import co.edu.usbcali.vehiculosnotificacion.dto.request.CreateUserRequest;
import co.edu.usbcali.vehiculosnotificacion.dto.response.GetUserResponse;
import co.edu.usbcali.vehiculosnotificacion.dto.response.UpdateUserResponse;
import co.edu.usbcali.vehiculosnotificacion.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static GetUserResponse entityToGetUserResponse(User user){

        //instanciar nuevo objeto
        GetUserResponse getUserResponse = GetUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .timezone(user.getTimezone())
                .isActive(user.getIsActive())
                .build();
        return getUserResponse;

    }

    public static List<GetUserResponse> entityToListGetUserResponse(List<User> users) {

        /*
        //instancias lista de objetos GetUserResponse
        List<GetUserResponse> getUserResponseList = new ArrayList<>();

        //Iterar sobre la lista de objetos Movie y agregarlas a la lista de objetos GetUserResponse
        for(int i = 0; i < users.size(); i++) {

            //por cada iteración, obtener el obejto user actual y convertirlo a DTO GetUserResponse
            User user = users.get(i);
            GetUserResponse getUserResponse = entityToGetUserResponse(user);

            //agregar el objeto DTO GetUserResponse a la lista de objetos GetUserResponse
            getUserResponseList.add(getUserResponse);
        }

        //retornar la lista DTO GetUserResponse
        return getUserResponseList;
        */

        return users.stream().map(UserMapper::entityToGetUserResponse).toList();

    }

    public static User createUserRequestToEntity(CreateUserRequest createUserRequest){

        /*
        User user = new User();

        //Asignar valores a los atributos del objeto User
        user.setEmail(createUserRequest.getEmail());
        user.getPhone(createUserRequest.getPhone());

        //retornar objeto User
        return  user;
        */

        return User.builder()
                .email(createUserRequest.getEmail())
                .phone(createUserRequest.getPhone())
                .password(createUserRequest.getPassword())
                .fullName(createUserRequest.getFullName())
                .timezone(createUserRequest.getTimezone())
                .isActive(createUserRequest.getIsActive())
                .build();

    }


    //convierte entidad a update response
    public static UpdateUserResponse entityToUpdateUserResponse(User user){

        //instanciar nuevo objeto response
        UpdateUserResponse response = UpdateUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .timezone(user.getTimezone())
                .isActive(user.getIsActive())
                .build();

        //retorna response
        return response;
    }

    //borrar objeto


}
