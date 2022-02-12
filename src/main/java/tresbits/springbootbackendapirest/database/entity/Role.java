package tresbits.springbootbackendapirest.database.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


@Data
@Entity
@Table(name = "roles")
public class Role implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, name = "nombre", length = 20)
    private String nombre;

    private static final long serialVersionUID = 1l;
}
