package TP2_LabsJava.controller;

import TP2_LabsJava.dto.ConceptoLaboralDTO;
import TP2_LabsJava.dto.JornadaDTO;
import TP2_LabsJava.dto.ResponseDTO;
import TP2_LabsJava.service.IJornadaService;
import TP2_LabsJava.validator.JornadaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping("/jornada")
public class JornadaController {

    @Autowired
    private IJornadaService jornadaService;

    @Autowired
    JornadaValidator jornadaValidator;

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody JornadaDTO jornadaDTO) {
        ResponseDTO responseDTO = jornadaService.agregarJornada(jornadaDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> findAllByFechaAndDocumento(@RequestParam(required = false) String fechaDesde, @RequestParam(required = false) String fechaHasta, @RequestParam(required = false) String nroDocumento) {
        List<ResponseDTO> jornadas = jornadaService.obtenerJornada(fechaDesde, fechaHasta, nroDocumento);
        return ResponseEntity.ok(jornadas);
    }

}
