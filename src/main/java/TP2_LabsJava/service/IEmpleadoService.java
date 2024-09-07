package TP2_LabsJava.service;

import TP2_LabsJava.dto.EmpleadoDTO;
import java.util.List;

public interface IEmpleadoService {

     EmpleadoDTO agregarEmpleado(EmpleadoDTO empleadoDTO);
     List<EmpleadoDTO> obtenerEmpleados();
     EmpleadoDTO obtenerEmpleadoPorId(Long id);
     EmpleadoDTO update(Long id, EmpleadoDTO empleadoDTO);
     void eliminarEmpleado(Long id);
}
