package cibertec.edu.pe.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relación muchos a uno entre Venta y Usuario
    // Una venta está asociada a un único usuario, pero un usuario puede tener muchas ventas
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") // "usuario_id" es la columna en la tabla "Ventas" que actúa como clave foránea
    private Usuario usuario;

    // Relación muchos a uno entre Venta y Libro
    // Una venta está asociada a un único libro, pero un libro puede estar en muchas ventas
    @ManyToOne
    @JoinColumn(name = "libro_id", referencedColumnName = "id") // "libro_id" es la columna en la tabla "Ventas" que actúa como clave foránea
    private Libro libro;

    private Integer cantidad;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private Double total;
}
