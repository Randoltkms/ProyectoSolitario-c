package cibertec.edu.pe.demo.response;

import cibertec.edu.pe.demo.dto.UserDetailDto;

public record FindUserResponse(String code,
                               String error,
                               UserDetailDto userDetailDto) {

}