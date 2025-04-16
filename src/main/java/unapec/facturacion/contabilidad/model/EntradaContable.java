package unapec.facturacion.contabilidad.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Data
public class EntradaContable {
    public String descripcion;
    public String sistemaAuxiliarId = "3";
    public String fecha;

    private List<DetallesEntradaContable> Detalles;
}
