package tresbits.springbootbackendapirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tresbits.springbootbackendapirest.models.entity.Cliente;
import tresbits.springbootbackendapirest.models.entity.Region;

public interface IClienteService {
    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public Cliente findById(long id);

    public Cliente save(Cliente cliente);

    public void delete(long id);

    public List<Cliente> findAllQuery(String nombre);

    public List<Region> findAllRegiones();

}
