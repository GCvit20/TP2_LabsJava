package TP2_LabsJava.entity;

import TP2_LabsJava.dto.EmpleadoDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nro_documento")
    private Integer nroDocumento;

    @Column(length = 30, nullable = false)
    private String nombre;
    @Column(length = 30, nullable = false)
    private String apellido;
    @Column(length = 30, nullable = false)
    private String email;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "nro_ingreso")
    private LocalDate fechaIngreso;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    public EmpleadoDTO toDTO() {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(this.getId());
        dto.setNombre(this.getNombre());
        dto.setApellido(this.getApellido());
        dto.setEmail(this.getEmail());
        dto.setNroDocumento((this.getNroDocumento()));
        dto.setFechaNacimiento(this.getFechaNacimiento());
        dto.setFechaIngreso(this.getFechaIngreso());
        dto.setFechaCreacion(this.getFechaCreacion());
        return dto;
    }

}
