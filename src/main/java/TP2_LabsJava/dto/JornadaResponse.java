package TP2_LabsJava.dto;

import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.entity.Jornada;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JornadaResponse {

    private Long id;
    private Integer nroDocumento;
    private String nombreCompleto;
    private LocalDate fecha;
    private String concepto;
    private Integer hsTrabajadas;

    public static JornadaResponse from(Jornada jornada, Empleado empleado, String conceptoLaboral) {
        return JornadaResponse.builder()
                .id(jornada.getId())
                .nroDocumento(empleado.getNroDocumento())
                .nombreCompleto(empleado.getNombre() + " " + empleado.getApellido())
                .fecha(jornada.getFecha())
                .concepto(conceptoLaboral)
                .hsTrabajadas(jornada.getHorasTrabajadas() != null ? jornada.getHorasTrabajadas() : null)
                .build();
    }
}
