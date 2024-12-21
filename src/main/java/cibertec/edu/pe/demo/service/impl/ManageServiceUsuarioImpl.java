package cibertec.edu.pe.demo.service.impl;

import cibertec.edu.pe.demo.dto.UserDetailDto;
import cibertec.edu.pe.demo.dto.UserDto;
import cibertec.edu.pe.demo.dto.UserUpdateDto;
import cibertec.edu.pe.demo.entity.Usuario;
import cibertec.edu.pe.demo.repository.UserRepository;
import cibertec.edu.pe.demo.service.ManageServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@Service
public class ManageServiceUsuarioImpl implements ManageServiceUsuario {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<UserDto> getAllUsers() throws Exception {

        List<UserDto> users = new ArrayList<UserDto>();
        Iterable<Usuario> iterable = userRepository.findAll();
        iterable.forEach(user -> {
            users.add(new UserDto(user
                    .getId(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName()));
        });
        return users;

    }

    @Override
    public Optional<UserDetailDto> getUserById(int id) throws Exception {
        Optional<Usuario> optional = userRepository.findById(id);
        return optional.map(user -> new UserDetailDto(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()));
    }

    @Override
    public boolean updateUser(UserDto userDto) throws Exception {
        Optional<Usuario> optional = userRepository.findById(userDto.id());
        return optional.map(user -> {
            user.setEmail(userDto.email());
            user.setFirstName(userDto.firstName());
            user.setLastName(userDto.lastName());
            userRepository.save(user);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean deleteUserById(int id) throws Exception {
        Optional<Usuario> optional = userRepository.findById(id);
        return optional.map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addUser(UserUpdateDto userUpdateDto) throws Exception {
        Optional<Usuario> optional = userRepository.findById(userUpdateDto.id());
        if (optional.isPresent())
            return false;
        else {
            Usuario user = new Usuario();
            user.setUsername(userUpdateDto.username());
            user.setPassword(userUpdateDto.password());
            user.setEmail(userUpdateDto.email());
            user.setFirstName(userUpdateDto.firstName());
            user.setLastName(userUpdateDto.lastName());
            user.setRole(userUpdateDto.role());
            user.setCreatedAt(new Date());
            userRepository.save(user);
            return true;
        }
    }
}
