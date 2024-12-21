package cibertec.edu.pe.demo.dto;

import java.util.Date;

public record UserUpdateDto(Integer id,
                            String username,
                            String password,
                            String email,
                            String firstName,
                            String lastName,
                            String role,
                            Date createdAt,
                            Date updatedAt) {
}
