package cibertec.edu.pe.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Autores")  // Cambié el nombre de la tabla para que coincida con el de la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    // Relación con la entidad Libro
    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;
}

