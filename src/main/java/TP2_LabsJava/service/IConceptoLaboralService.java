package TP2_LabsJava.service;

import TP2_LabsJava.dto.ConceptoLaboralDTO;
import java.util.List;

public interface IConceptoLaboralService {

    List<ConceptoLaboralDTO> obtenerConceptosLaborales(Long id, String nombre);
}
