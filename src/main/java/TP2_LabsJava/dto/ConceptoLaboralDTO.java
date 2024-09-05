package TP2_LabsJava.dto;

import TP2_LabsJava.entity.ConceptoLaboral;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConceptoLaboralDTO {

    private Long id;
    private String nombre;
    private Boolean laborable;
    private Integer hsMinimo;
    private Integer hsMaximo;

    public ConceptoLaboral toEntity() {
        ConceptoLaboral conceptoLaboral = new ConceptoLaboral();
        conceptoLaboral.setId(this.getId());
        conceptoLaboral.setNombre(this.getNombre());
        conceptoLaboral.setLaborable(this.getLaborable());

        if (this.getHsMinimo() != null) {
            conceptoLaboral.setHsMinimo(this.getHsMinimo());
        }

        if (this.getHsMaximo() != null) {
            conceptoLaboral.setHsMaximo(this.getHsMaximo());
        }

        return conceptoLaboral;
    }
}
