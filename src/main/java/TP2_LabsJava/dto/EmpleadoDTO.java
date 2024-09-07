package TP2_LabsJava.dto;

import TP2_LabsJava.entity.Empleado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {

    private Long id;
    private Integer nroDocumento;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private LocalDate fechaCreacion;

    public Empleado toEntity() {
        Empleado empleado = new Empleado();
        empleado.setId(this.getId());
        empleado.setNombre(this.getNombre());
        empleado.setApellido(this.getApellido());
        empleado.setEmail(this.getEmail());
        empleado.setNroDocumento((this.getNroDocumento()));
        empleado.setFechaNacimiento(this.getFechaNacimiento());
        empleado.setFechaIngreso(this.getFechaIngreso());
        return empleado;
    }
}
