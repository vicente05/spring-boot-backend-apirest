package tresbits.springbootbackendapirest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tresbits.springbootbackendapirest.database.dao.IUsuarioDao;
import tresbits.springbootbackendapirest.database.entity.Usuario;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByEmail(username).orElse(null);

        if (usuario == null) {
            logger.error("Error en el login: no existe el usuario '" + username + "' en el sistema");
            throw new UsernameNotFoundException("Error en el login: no existe el usuario '" + username + "' en el sistema");
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getNombre()))
            .peek(authority -> logger.info("Role: " + authority.getAuthority()))
            .collect(Collectors.toList());

        return new User(usuario.getEmail(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByEmail(String username) {
        return usuarioDao.findByEmail(username).orElse(null);
    }    
}
