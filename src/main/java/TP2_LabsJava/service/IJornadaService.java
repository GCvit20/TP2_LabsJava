package TP2_LabsJava.service;

import TP2_LabsJava.dto.JornadaDTO;
import TP2_LabsJava.dto.ResponseDTO;
import TP2_LabsJava.entity.Jornada;

import java.time.LocalDate;
import java.util.List;

public interface IJornadaService {

    ResponseDTO agregarJornada(JornadaDTO jornadaDTO);
    List<ResponseDTO> obtenerJornada(String fechaDesde, String fechaHasta, String nroDocumento);
}
