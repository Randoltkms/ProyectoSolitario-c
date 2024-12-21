package cibertec.edu.pe.demo.response;

import cibertec.edu.pe.demo.dto.UserDto;

import java.util.List;

public record FindUsersResponse(String code,
                                String error,
                                List<UserDto> users) {

}