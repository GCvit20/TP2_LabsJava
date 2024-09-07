package TP2_LabsJava.dto;

import TP2_LabsJava.entity.ConceptoLaboral;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
        conceptoLaboral.setHsMinimo(this.getHsMinimo());
        conceptoLaboral.setHsMaximo(this.getHsMaximo());

        return conceptoLaboral;
    }
}
