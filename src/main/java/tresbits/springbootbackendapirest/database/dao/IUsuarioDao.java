package tresbits.springbootbackendapirest.database.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tresbits.springbootbackendapirest.database.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
    @Query(nativeQuery = true, value = "select u.* from Usuarios u where u.email = :email")
    public Usuario findByEmail( String email);

    @Query(nativeQuery = true, value = "select u.* from Usuarios u where u.username = :username")
    public Usuario findByUsername2( String username);
}
