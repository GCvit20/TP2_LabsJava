package TP2_LabsJava.controller;

import TP2_LabsJava.dto.ConceptoLaboralDTO;
import TP2_LabsJava.service.IConceptoLaboralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@RequestMapping("/concepto")
public class ConceptoLaboralController {

    @Autowired
    private IConceptoLaboralService conceptoLaboralService;

    @GetMapping
    public ResponseEntity<List<ConceptoLaboralDTO>> obtenerConceptosLaborales(@RequestParam(required = false) Long id, @RequestParam(required = false) String nombre) {
        List<ConceptoLaboralDTO> conceptos = conceptoLaboralService.obtenerConceptosLaborales(id, nombre);
        return ResponseEntity.ok(conceptos);
    }

}
