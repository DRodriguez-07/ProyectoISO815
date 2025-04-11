package unapec.facturacion.repository;

import org.springframework.data.repository.CrudRepository;
import unapec.facturacion.domain.Articulo;

public interface ArticuloRepository extends CrudRepository<Articulo, Long> {

}
