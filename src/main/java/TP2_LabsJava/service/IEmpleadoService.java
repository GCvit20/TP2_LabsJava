package TP2_LabsJava.service;

import TP2_LabsJava.dto.EmpleadoDTO;
import TP2_LabsJava.entity.Empleado;
import java.util.List;

public interface IEmpleadoService {

     void agregarEmpleado(Empleado empleado);
     List<EmpleadoDTO> obtenerEmpleados();
     EmpleadoDTO obtenerEmpleadoPorId(Long id);
     EmpleadoDTO update(Long id, EmpleadoDTO empleadoDTO);
}
