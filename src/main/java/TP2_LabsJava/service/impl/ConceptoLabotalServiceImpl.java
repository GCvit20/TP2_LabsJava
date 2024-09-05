package TP2_LabsJava.service.impl;

import TP2_LabsJava.dto.ConceptoLaboralDTO;
import TP2_LabsJava.entity.ConceptoLaboral;
import TP2_LabsJava.repository.IConceptoLaboralRepository;
import TP2_LabsJava.service.IConceptoLaboralService;
import TP2_LabsJava.validator.EmpleadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConceptoLabotalServiceImpl implements IConceptoLaboralService {

    @Autowired
    IConceptoLaboralRepository repository;

    @Autowired
    EmpleadoValidator empleadoValidator;

    @Override
    public List<ConceptoLaboralDTO> obtenerConceptosLaborales(Long id, String nombre) {

        if (id != null && nombre != null && !nombre.isEmpty()) {
            return buscarPorIdYNombre(id, nombre);
        } else if (id != null) {
            return buscarPorId(id);
        } else if (nombre != null && !nombre.isEmpty()) {
            return buscarPorNombre(nombre);
        } else {
            return buscarTodos();
        }
    }

    private List<ConceptoLaboralDTO> buscarPorIdYNombre(Long id, String nombre) {
        List<ConceptoLaboral> conceptos = repository.findByIdAndNombreContaining(id, nombre);
        return convertirAListaDTO(conceptos);
    }

    private List<ConceptoLaboralDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(concepto -> Collections.singletonList(concepto.toDTO()))
                .orElse(Collections.emptyList());
    }

    private List<ConceptoLaboralDTO> buscarPorNombre(String nombre) {
        List<ConceptoLaboral> conceptos = repository.findByNombreContaining(nombre);
        return convertirAListaDTO(conceptos);
    }

    private List<ConceptoLaboralDTO> buscarTodos() {
        List<ConceptoLaboral> conceptos = repository.findAll();
        return convertirAListaDTO(conceptos);
    }

    private List<ConceptoLaboralDTO> convertirAListaDTO(List<ConceptoLaboral> conceptos) {
        return conceptos.stream()
                .map(ConceptoLaboral::toDTO)
                .collect(Collectors.toList());
    }

}
