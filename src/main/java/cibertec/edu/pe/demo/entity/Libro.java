package cibertec.edu.pe.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    // Relación muchos a uno entre Libro y Autor
    // Un libro tiene un autor, pero un autor puede tener muchos libros


    private Double precio;

    @Column(length = 255)
    private String urlImagen;

    @ManyToOne
    @JoinColumn(name = "CategoriaId", referencedColumnName = "id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "AutorId", referencedColumnName = "id")
    private Autor autor;
}
