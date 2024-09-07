package TP2_LabsJava.service.impl;

import TP2_LabsJava.dto.EmpleadoDTO;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.exceptions.EmpleadoNoEncontradoException;
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
    IEmpleadoRepository empleadoRepository;

    @Autowired
    EmpleadoValidator empleadoValidator;

    @Override
    public EmpleadoDTO agregarEmpleado(EmpleadoDTO empleadoDTO) {

        Empleado empleado = empleadoDTO.toEntity();

        empleadoValidator.validarCamposObligatorios(empleado);
        empleadoValidator.validarNroDocumento(empleado);
        empleadoValidator.validarEmail(empleado);
        empleadoValidator.validarFechaNacimiento(empleado);
        empleadoValidator.validarFechaIngreso(empleado);
        empleadoValidator.validarNombreYApellido(empleado);

        Empleado savedEmpleado = this.empleadoRepository.save(empleado);
        return savedEmpleado.toDTO();
    }

    @Override
    public List<EmpleadoDTO> obtenerEmpleados() {
        List<Empleado> empleado = empleadoRepository.findAll();
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
        empleadoValidator.validarFechaNacimiento(empleadoExistente);
        empleadoValidator.validarFechaIngreso(empleadoExistente);
        empleadoValidator.validarNombreYApellido(empleadoExistente);

        this.empleadoRepository.save(empleadoExistente);

        return empleadoExistente.toDTO();
    }

    @Override
    public void eliminarEmpleado(Long id) {

        empleadoValidator.validarJornadasAsociadas(id);

        if (!empleadoRepository.existsById(id)) {
            throw new EmpleadoNoEncontradoException(id);
        }

        empleadoRepository.deleteById(id);
    }
}
