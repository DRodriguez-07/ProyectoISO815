package unapec.facturacion.repository;

import org.springframework.data.repository.CrudRepository;
import unapec.facturacion.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
