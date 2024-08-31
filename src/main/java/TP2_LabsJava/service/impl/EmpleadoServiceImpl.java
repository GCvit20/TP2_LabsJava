package TP2_LabsJava.service.impl;

import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.repository.EmpleadoRepository;
import TP2_LabsJava.service.IEmpleadoService;
import TP2_LabsJava.validator.EmpleadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    EmpleadoRepository repository;
    EmpleadoValidator empleadoValidator;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository repository, EmpleadoValidator empleadoValidator) {
        this.repository = repository;
        this.empleadoValidator = empleadoValidator;
    }

    @Override
    public Empleado agregarEmpleado(Empleado empleado) {

        empleadoValidator.validarCamposObligatorios(empleado);
        empleadoValidator.validarEdad(empleado);
        empleadoValidator.validarNroDocumento(empleado);
        empleadoValidator.validarEmail(empleado);
        empleadoValidator.validarFechaNacimiento(empleado);
        empleadoValidator.validarFechaIngreso(empleado);
        empleadoValidator.validarNombreYApellido(empleado);

        return this.repository.save(empleado);
    }

}
