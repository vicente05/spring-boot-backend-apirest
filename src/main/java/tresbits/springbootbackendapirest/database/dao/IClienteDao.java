package tresbits.springbootbackendapirest.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tresbits.springbootbackendapirest.database.entity.*;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

    public List<Cliente> findAllByOrderByIdAsc();

    @Query(value = "select * from clientes c where c.nombre= :firstName", nativeQuery = true)
    List<Cliente> getClientesNombre(String firstName);

    @Query("from Region")
    public List<Region> findAllRegiones();

}
