package TP2_LabsJava.service;

import TP2_LabsJava.dto.JornadaDTO;
import TP2_LabsJava.dto.JornadaResponse;
import java.util.List;

public interface IJornadaService {

    JornadaResponse agregarJornada(JornadaDTO jornadaDTO);
    List<JornadaResponse> obtenerJornada(String fechaDesde, String fechaHasta, String nroDocumento);
}
