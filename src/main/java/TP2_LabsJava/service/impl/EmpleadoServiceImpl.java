package TP2_LabsJava.service.impl;

import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.repository.EmpleadoRepository;
import TP2_LabsJava.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    EmpleadoRepository repository;

    @Override
    public Empleado agregarEmpleado(Empleado empleado) {
        return this.repository.save(empleado);
    }

    @Override
    public boolean esEmpleadoPorNumeroDocumento(Integer nroDocumento) {
        return repository.findByNroDocumento(nroDocumento).isPresent();
    }

    @Override
    public boolean esEmpleadoPorEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }
}
