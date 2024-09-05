package TP2_LabsJava.controller;

import TP2_LabsJava.dto.JornadaDTO;
import TP2_LabsJava.dto.ResponseDTO;
import TP2_LabsJava.service.IJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/jornada")
public class JornadaController {

    @Autowired
    private IJornadaService jornadaService;

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody JornadaDTO jornadaDTO) {

        ResponseDTO responseDTO = jornadaService.agregarJornada(jornadaDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
