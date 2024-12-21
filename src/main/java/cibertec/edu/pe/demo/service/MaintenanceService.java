package cibertec.edu.pe.demo.service;

import cibertec.edu.pe.demo.dto.AutorDto;

import java.util.List;
import java.util.Optional;

public interface MaintenanceService {

    /* getAll Parte de un servicio o repositorio en tu aplicación Spring Boot.
    Su función principal es recuperar y devolver una lista de todos los autores
    almacenados en la base de datos. */
        List<AutorDto>getAllAuthors() throws Exception;

    // Obtener un autor por su ID
    AutorDto getAutormById(int id);


    // Actualizar un autor
    Boolean updateAuthor(AutorDto autorDto) ;

    // Eliminar un autor por su ID
    Boolean deleteAuthorById(int id) ;

    // Agregar un nuevo autor
    Boolean addAuthor(AutorDto autorDto) throws Exception;





}
