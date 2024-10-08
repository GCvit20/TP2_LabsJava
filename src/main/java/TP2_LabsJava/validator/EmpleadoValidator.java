package TP2_LabsJava.validator;

import TP2_LabsJava.dto.EmpleadoDTO;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.exceptions.*;
import TP2_LabsJava.repository.IConceptoLaboralRepository;
import TP2_LabsJava.repository.IEmpleadoRepository;
import TP2_LabsJava.repository.IJornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class EmpleadoValidator {

    @Autowired
    private IEmpleadoRepository empleadopRepository;

    @Autowired
    private IConceptoLaboralRepository conceptoLaboralRepository;

    @Autowired
    private IJornadaRepository jornadaRepository;

    private boolean esEdadValida(LocalDate fechaNacimiento) {
        return fechaNacimiento != null && Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }

    private boolean esEmpleadoPorNumeroDocumento(Integer nroDocumento) {
        Optional<Empleado> empleado = empleadopRepository.findByNroDocumento(nroDocumento);
        return empleado.isPresent();
    }

    public void validarNroDocumento(Empleado empleado) {
        if (esEmpleadoPorNumeroDocumento(empleado.getNroDocumento())) {
            throw new NroDocumentoInvalidoException(empleado.getNroDocumento());
        }
    }

    private boolean esEmpleadoPorEmail(String email) {
        Optional<Empleado> empleado = empleadopRepository.findByEmail(email);
        return empleado.isPresent();
    }

    private boolean esEmailValido(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email != null && pattern.matcher(email).matches();
    }

    public void validarEmail(Empleado empleado) {
        if(esEmpleadoPorEmail(empleado.getEmail())) {
            throw new EmailDuplicadoException(empleado.getEmail());
        }
        if (!esEmailValido(empleado.getEmail())) {
            throw new FormatoEmailInvalidoException(empleado.getEmail());
        }
    }

    public void validarFechaNacimiento(Empleado empleado) {
        if(empleado.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new FechaNacimientoInvalidaException(empleado.getFechaNacimiento());
        }
        if (!esEdadValida(empleado.getFechaNacimiento())) {
            throw new EdadInvalidaException(empleado.getFechaNacimiento());
        }
    }

    public void validarFechaIngreso(Empleado empleado) {

        if (empleado.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new FechaIngresoInvalidaException(empleado.getFechaIngreso());
        }
    }

    private boolean esNombreValido(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return true;
        }

        String letrasRegex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(letrasRegex);
        return pattern.matcher(nombre).matches();
    }

    private boolean esApellidoValido(String apellido) {

        if (apellido == null || apellido.trim().isEmpty()) {
            return true;
        }

        String letrasRegex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(letrasRegex);
        return pattern.matcher(apellido).matches();
    }

    public void validarNombreYApellido(Empleado empleado) {
        if(!esNombreValido(empleado.getNombre())) {
            throw new FormatoNombreInvalidoException(empleado.getNombre());
        }
        if(!esApellidoValido(empleado.getApellido())) {
            throw new FormatoApellidoInvalidoException(empleado.getApellido());
        }
    }

    public void validarCamposObligatorios(Empleado empleado) {

        if (empleado.getNroDocumento() == null) {
            throw new IllegalArgumentException("‘nroDocumento’ es obligatorio.");
        }
        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("‘nombre’ es obligatorio.");
        }
        if (empleado.getApellido() == null || empleado.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("‘apellido’ es obligatorio.");
        }
        if (empleado.getEmail() == null || empleado.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("‘email’ es obligatorio.");
        }
        if (empleado.getFechaNacimiento() == null) {
            throw new IllegalArgumentException("‘fechaNacimiento’ es obligatorio.");
        }
        if (empleado.getFechaIngreso() == null) {
            throw new IllegalArgumentException("‘fechaIngreso’ es obligatorio.");
        }
    }

    public Empleado validarEmpleadoExistente(Long id) {

        return empleadopRepository.findById(id).orElseThrow(() -> new IdInexistenteException(id));
    }

    public void validarUnicidadDeNroDocumentoYEmail(EmpleadoDTO empleadoDTO, Long id) {
        if (empleadoDTO.getNroDocumento() != null) {
            verificarUnicidadDni(empleadoDTO.getNroDocumento(), id);
        }
        if (empleadoDTO.getEmail() != null) {
            verificarUnicidadEmail(empleadoDTO.getEmail(), id);
        }
    }

    private void verificarUnicidadDni(Integer nroDocumento, Long id) {
        if (this.empleadopRepository.existsByNroDocumentoAndIdNot(nroDocumento, id)) {
            throw new NroDocumentoInvalidoException(nroDocumento);
        }
    }

    private void verificarUnicidadEmail(String email, Long id) {
        if (this.empleadopRepository.existsByEmailAndIdNot(email, id)) {
            throw new EmailDuplicadoException(email);
        }
    }

    public void validarJornadasAsociadas(Long empleadoId) {
        if (jornadaRepository.existsByEmpleadoId(empleadoId)) {
            throw new IllegalArgumentException("No es posible eliminar un empleado con jornadas asociadas.");
        }
    }

}
