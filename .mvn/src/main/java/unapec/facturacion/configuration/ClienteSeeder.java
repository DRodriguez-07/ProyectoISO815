package unapec.facturacion.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import unapec.facturacion.domain.Cliente;
import unapec.facturacion.domain.Vendedor;
import unapec.facturacion.repository.ClienteRepository;
import unapec.facturacion.repository.VendedorRepository;

@Configuration
public class ClienteSeeder implements ApplicationRunner {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteSeeder(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(clienteRepository.findById(1L).isEmpty()) {
            addInitialData();
        }
    }

    public void addInitialData() {
        clienteRepository.save(new Cliente(null,"AES ANDRES DR S A", "131137814",86, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null,"AGROINDUSTRIAL FERRETERA SRL", "101541377",87, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null,"CARTONES DEL CARIBE SAS", "101682515",88, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "CERVECERIA NACIONAL DOMINICANA S A", "101003723",89, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "DISTRIBUIDORA CORRIPIO S A S", "101003693",90, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "EL CATADOR S A", "101060702",91, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "HIPERMERCADOS OLE S A", "101532483",92, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "INDUSTRIAS BISONO SRL", "101621516",93, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "IQTEK SOLUTIONS SRL", "130876967",94, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "JARABA IMPORT S A", "101642892",95, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "MAX DISTRIBUCION SRL", "122027612",96, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "NESTLE DOMINICANA S A", "101829168",97, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "PLAZA LAMA S A", "101171111",98, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "BANCO MULTIPLE VIMENCA S A", "101021411",99, Cliente.EstadoCliente.ACTIVO, null));
        clienteRepository.save(new Cliente(null, "GRUPO BHD S A", "101106352",100, Cliente.EstadoCliente.ACTIVO, null));
    }
}
