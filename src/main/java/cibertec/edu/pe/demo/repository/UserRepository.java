package cibertec.edu.pe.demo.repository;

import cibertec.edu.pe.demo.entity.Usuario;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository  extends CrudRepository<Usuario, Integer> {
}
