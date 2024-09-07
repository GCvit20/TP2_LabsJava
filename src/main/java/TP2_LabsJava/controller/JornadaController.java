package TP2_LabsJava.controller;

import TP2_LabsJava.dto.JornadaDTO;
import TP2_LabsJava.dto.JornadaResponse;
import TP2_LabsJava.service.IJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/jornada")
public class JornadaController {

    @Autowired
    private IJornadaService jornadaService;

    @PostMapping
    public ResponseEntity<JornadaResponse> save(@Valid @RequestBody JornadaDTO jornadaDTO) {
        JornadaResponse jornadaResponse = jornadaService.agregarJornada(jornadaDTO);
        return ResponseEntity.ok(jornadaResponse);
    }

    @GetMapping
    public ResponseEntity<List<JornadaResponse>> findAllByFechaAndDocumento(@RequestParam(required = false) String fechaDesde, @RequestParam(required = false) String fechaHasta, @RequestParam(required = false) String nroDocumento) {
        List<JornadaResponse> jornadas = jornadaService.obtenerJornada(fechaDesde, fechaHasta, nroDocumento);
        return ResponseEntity.ok(jornadas);
    }

}
