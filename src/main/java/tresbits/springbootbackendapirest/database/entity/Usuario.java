package tresbits.springbootbackendapirest.database.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, length = 20, name = "username")
    private String username;

    @Column(length = 60, name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(length = 255, name = "email", unique = true)
    private String email;

    @Column(length = 255, name = "nombre")
    private String nombre;

    @Column(length = 255, name = "apellido")
    private String apellido;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "usuarios_roles", 
        joinColumns = @JoinColumn(name = "usuario_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})}
    )
    private List<Role> roles;
    
    private static final long serialVersionUID = 1l;
}
