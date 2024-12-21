package cibertec.edu.pe.demo.dto;

import java.util.Date;

public record UserDetailDto(Integer id,
                            String username,
                            String email,
                            String firstName,
                            String lastName,
                            String role,
                            Date createdAt,
                            Date updatedAt) {

}
