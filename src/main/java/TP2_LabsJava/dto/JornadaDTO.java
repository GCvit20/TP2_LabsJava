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
public class JornadaDTO {

    private Long id;
    private LocalDate fecha;
    private Integer horasTrabajadas;
    private Long idEmpleado;
    private Long idConcepto;

    public Jornada toEntity() {
        Jornada jornada = new Jornada();
        Empleado empleado = new Empleado();
        empleado.setId(this.idEmpleado);
        jornada.setEmpleado(empleado);

        ConceptoLaboral conceptoLaboral = new ConceptoLaboral();
        conceptoLaboral.setId(this.idConcepto);
        jornada.setConceptoLaboral(conceptoLaboral);

        jornada.setFecha(this.getFecha());
        jornada.setHorasTrabajadas(this.getHorasTrabajadas());
        return jornada;
    }
}
