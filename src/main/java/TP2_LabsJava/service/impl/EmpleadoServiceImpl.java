package TP2_LabsJava.service.impl;

import TP2_LabsJava.dto.EmpleadoDTO;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.repository.IEmpleadoRepository;
import TP2_LabsJava.service.IEmpleadoService;
import TP2_LabsJava.validator.EmpleadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    IEmpleadoRepository repository;

    @Autowired
    EmpleadoValidator empleadoValidator;

    @Override
    public void agregarEmpleado(Empleado empleado) {

        empleadoValidator.validarCamposObligatorios(empleado);
        empleadoValidator.validarEdad(empleado);
        empleadoValidator.validarNroDocumento(empleado);
        empleadoValidator.validarEmail(empleado);
        empleadoValidator.validarFechaNacimiento(empleado);
        empleadoValidator.validarFechaIngreso(empleado);
        empleadoValidator.validarNombreYApellido(empleado);

        this.repository.save(empleado);
    }

    @Override
    public List<EmpleadoDTO> obtenerEmpleados() {
        List<Empleado> empleado = repository.findAll();
         return empleado.stream()
                .map(Empleado::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmpleadoDTO obtenerEmpleadoPorId(Long id) {
        Empleado empleado = empleadoValidator.validarEmpleadoExistente(id);
        return empleado.toDTO();
    }

    private void actualizarDatosEmpleado(Empleado empleadoExistente, Empleado empleadoActualizado) {
        empleadoExistente.setNroDocumento(empleadoActualizado.getNroDocumento());
        empleadoExistente.setEmail(empleadoActualizado.getEmail());
        empleadoExistente.setNombre(empleadoActualizado.getNombre());
        empleadoExistente.setApellido(empleadoActualizado.getApellido());
        empleadoExistente.setFechaNacimiento(empleadoActualizado.getFechaNacimiento());
        empleadoExistente.setFechaIngreso(empleadoActualizado.getFechaIngreso());
    }

    @Override
    public EmpleadoDTO update(Long id, EmpleadoDTO empleadoDTO) {

        Empleado empleadoExistente = empleadoValidator.validarEmpleadoExistente(id);
        Empleado empleadoActualizado = empleadoDTO.toEntity();
        actualizarDatosEmpleado(empleadoExistente, empleadoActualizado);

        empleadoValidator.validarUnicidadDeNroDocumentoYEmail(empleadoDTO, id);
        empleadoValidator.validarCamposObligatorios(empleadoExistente);
        empleadoValidator.validarEdad(empleadoExistente);
        empleadoValidator.validarFechaNacimiento(empleadoExistente);
        empleadoValidator.validarFechaIngreso(empleadoExistente);
        empleadoValidator.validarNombreYApellido(empleadoExistente);

        this.repository.save(empleadoExistente);

        return empleadoExistente.toDTO();
    }

}
