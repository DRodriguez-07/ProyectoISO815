package unapec.facturacion.controller.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unapec.facturacion.domain.Factura;
import unapec.facturacion.dto.FacturaDto;
import unapec.facturacion.repository.FacturaRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaApiController {
    private FacturaRepository facturaRepository;

    public FacturaApiController(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @GetMapping("")
    public Iterable<FacturaDto> index(@RequestParam(value = "clienteId", required = false) Long clienteId) {
        return facturaRepository.searchFacturasByCliente_Id(clienteId);
    }
}
