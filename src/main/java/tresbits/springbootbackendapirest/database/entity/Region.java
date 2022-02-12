package tresbits.springbootbackendapirest.database.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity()
@Table(name = "regiones")
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "nombre", length = 255)
    private String nombre;

    private static final long serialVersionUID = 1l;

}
