package cibertec.edu.pe.demo.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record AutorDto(Integer id, String nombre,  @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento) {
}
