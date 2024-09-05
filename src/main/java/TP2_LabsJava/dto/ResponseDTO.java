package TP2_LabsJava.dto;

import TP2_LabsJava.entity.ConceptoLaboral;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.entity.Jornada;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private Long id;
    private Integer nroDocumento;
    private String nombreCompleto;
    private LocalDate fecha;
    private String concepto;
    private Integer hsTrabajadas;

    // Método estático de fábrica para crear el DTO desde las entidades
    public static ResponseDTO from(Jornada jornada, Empleado empleado, String conceptoLaboral) {
        return ResponseDTO.builder()
                .id(jornada.getId())
                .nroDocumento(empleado.getNroDocumento())
                .nombreCompleto(empleado.getNombre() + " " + empleado.getApellido())
                .fecha(jornada.getFecha())
                .concepto(conceptoLaboral)
                .hsTrabajadas(jornada.getHorasTrabajadas())
                .build();
    }
}
