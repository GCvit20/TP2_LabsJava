package TP2_LabsJava.service;

import TP2_LabsJava.dto.JornadaDTO;
import TP2_LabsJava.dto.ResponseDTO;
import TP2_LabsJava.entity.Jornada;

public interface IJornadaService {

    ResponseDTO agregarJornada(JornadaDTO jornadaDTO);
}
