package cibertec.edu.pe.demo.api;
import cibertec.edu.pe.demo.dto.UserDetailDto;
import cibertec.edu.pe.demo.dto.UserDto;
import cibertec.edu.pe.demo.dto.UserUpdateDto;
import cibertec.edu.pe.demo.response.*;
import cibertec.edu.pe.demo.service.ManageServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-user")
public class ManageUserApi {
    @Autowired
    ManageServiceUsuario manageServiceUsuario;


    @GetMapping("/all")
    public FindUsersResponse findUsers() {

        try {
            List<UserDto> users = manageServiceUsuario.getAllUsers();
            if (!users.isEmpty())
                return new FindUsersResponse("01", null, users);
            else
                return new FindUsersResponse("02", "Users not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindUsersResponse("99", "An error occurred, please try again", null);

        }

    }

    @GetMapping("/detail")
    public FindUserResponse findUser(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            Optional<UserDetailDto> optional = manageServiceUsuario.getUserById(Integer.parseInt(id));
            return optional.map(user ->
                    new FindUserResponse("01", null, user)
            ).orElse(
                    new FindUserResponse("02", "User not found", null)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new FindUserResponse("99", "An error occurred, please try again", null);

        }

    }

    @PutMapping("/update")
    public UpdateUserResponse updateUser(@RequestBody UserDto userDto) {

        try {
            if (manageServiceUsuario.updateUser(userDto))
                return new UpdateUserResponse("01", null);
            else
                return new UpdateUserResponse("02", "User not found");

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateUserResponse("99", "An error occurred, please try again");

        }

    }

    @DeleteMapping("/delete/{id}")
    public DeleteUserResponse deleteUser(@PathVariable String id) {

        try {
            if (manageServiceUsuario.deleteUserById(Integer.parseInt(id)))
                return new DeleteUserResponse("01", null);
            else
                return new DeleteUserResponse("02", "User not found");

        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteUserResponse("99", "An error occurred, please try again");

        }

    }

    @PostMapping("/create")
    public CreateUserResponse createUser(@RequestBody UserUpdateDto userUpdateDto) {

        try {
            if (manageServiceUsuario.addUser(userUpdateDto))
                return new CreateUserResponse("01", null);
            else
                return new CreateUserResponse("02", "User already exists");

        } catch (Exception e) {
            e.printStackTrace();
            return new CreateUserResponse("99", "An error occurred, please try again");

        }

    }


}
