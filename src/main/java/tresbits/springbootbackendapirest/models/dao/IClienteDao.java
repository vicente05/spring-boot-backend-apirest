package tresbits.springbootbackendapirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import tresbits.springbootbackendapirest.models.entity.*;

public interface IClienteDao extends JpaRepository<Cliente, Long> {
    public List<Cliente> findAllByOrderByIdAsc();
}
