package TP2_LabsJava.entity;

import TP2_LabsJava.dto.ConceptoLaboralDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="concepto_laboral")
public class ConceptoLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Getter
    private Boolean laborable;

    @Column(name = "hs_minimo")
    private Integer hsMinimo;

    @Column(name = "hs_maximo")
    private Integer hsMaximo;

    public ConceptoLaboralDTO toDTO() {
        ConceptoLaboralDTO dto = new ConceptoLaboralDTO();
        dto.setId(this.getId());
        dto.setNombre(this.getNombre());
        dto.setLaborable(this.getLaborable());
        dto.setHsMinimo(this.getHsMinimo());
        dto.setHsMaximo((this.getHsMaximo()));
        return dto;
    }
}
