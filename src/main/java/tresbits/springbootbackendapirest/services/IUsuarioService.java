package tresbits.springbootbackendapirest.services;

import tresbits.springbootbackendapirest.database.entity.Usuario;

public interface IUsuarioService {
    public Usuario findByEmail( String email);
}
