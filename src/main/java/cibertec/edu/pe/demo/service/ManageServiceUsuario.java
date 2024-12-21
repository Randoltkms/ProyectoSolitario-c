package cibertec.edu.pe.demo.service;

import cibertec.edu.pe.demo.dto.UserDetailDto;
import cibertec.edu.pe.demo.dto.UserDto;
import cibertec.edu.pe.demo.dto.UserUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ManageServiceUsuario {

    List<UserDto> getAllUsers() throws Exception;

    Optional<UserDetailDto> getUserById(int id) throws Exception;

    boolean updateUser(UserDto userDto) throws Exception;

    boolean deleteUserById(int id) throws Exception;

    boolean addUser(UserUpdateDto userUpdateDto) throws Exception;

}
