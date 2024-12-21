    package cibertec.edu.pe.demo.service.impl;

    import cibertec.edu.pe.demo.dto.AutorDto;
    import cibertec.edu.pe.demo.entity.Autor;
    import cibertec.edu.pe.demo.repository.AutorRepository;
    import cibertec.edu.pe.demo.service.MaintenanceService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class MaintenanceServiceImpl implements MaintenanceService {

        @Autowired
        AutorRepository autorRepository;

        @Override
        public List<AutorDto> getAllAuthors() {

            // Crear una lista vacía para los DTOs
            List<AutorDto> authors = new ArrayList<AutorDto>();

            // Obtener todos los autores desde el repositorio
            Iterable<Autor> iterable = autorRepository.findAll();

            // Convertir cada autor a un DTO y agregarlo a la lista directamente
            iterable.forEach(autor -> authors.add(new AutorDto(
                    autor.getId(),
                    autor.getNombre(),
                    autor.getFechaNacimiento()
            )));

            // Retornar la lista de autores DTO
            return authors;
        }



        @Override
        public AutorDto getAutormById(int id) {
            Optional<Autor> optional = autorRepository.findById(id);
            // Si no se encuentra el autor, retorna null, de lo contrario retorna el AutorDetailDto
            return optional.map(autor -> new AutorDto(
                    autor.getId(),
                    autor.getNombre(),
                    autor.getFechaNacimiento()
            )).orElse(null); // Si no se encuentra el autor, devuelve null
        }


        @Override
        public Boolean updateAuthor(AutorDto autorDto)  {
            Optional<Autor> optional = autorRepository.findById(autorDto.id());
            return optional.map(autor -> {
                autor.setNombre(autorDto.nombre());
                autor.setFechaNacimiento(autorDto.fechaNacimiento());
                autorRepository.save(autor);
                return true;
            }).orElse(false);
        }

        @Override
        public Boolean deleteAuthorById(int id) {
            Optional<Autor> optional = autorRepository.findById(id);
            return optional.map(autor -> {
                autorRepository.delete(autor);
                return true;
            }).orElse(false);
        }


        @Override
        public Boolean addAuthor(AutorDto autorDto) {
            try {
                // Crear un nuevo objeto Autor
                Autor autor = new Autor();
                autor.setNombre(autorDto.nombre());
                autor.setFechaNacimiento(autorDto.fechaNacimiento());

                // Guardar el autor en la base de datos
                autorRepository.save(autor);
                return true;  // El autor fue agregado correctamente
            } catch (Exception e) {
                // Captura de excepciones en caso de errores
                e.printStackTrace();
                return false;  // Retorna false si ocurrió algún error
            }
        }

    }


