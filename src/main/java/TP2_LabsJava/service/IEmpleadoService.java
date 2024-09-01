package TP2_LabsJava.service;

import TP2_LabsJava.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoService {

     Empleado agregarEmpleado(Empleado empleado);
     List<Empleado> obtenerEmpleados();
     Empleado obtenerEmpleadoPorId(Long id);
}
