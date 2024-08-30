package TP2_LabsJava.service;

import TP2_LabsJava.entity.Empleado;

public interface IEmpleadoService {

     Empleado agregarEmpleado(Empleado empleado);
     boolean esEmpleadoPorNumeroDocumento(Integer nroDocumento);
     boolean esEmpleadoPorEmail(String email);
}
