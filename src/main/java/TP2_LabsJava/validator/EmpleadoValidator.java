package TP2_LabsJava.validator;

import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.exceptions.*;
import TP2_LabsJava.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class EmpleadoValidator {

    private EmpleadoRepository repository;
    private LocalDate fechaActual = LocalDate.now();

    @Autowired
    public EmpleadoValidator(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public boolean esEdadValida(LocalDate fechaNacimiento) {
        return fechaNacimiento != null && Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }

    public void validarEdad(Empleado empleado) {
        if (!esEdadValida(empleado.getFechaNacimiento())) {
            throw new EdadInvalidaException(empleado.getFechaNacimiento());
        }
    }

    public boolean esEmpleadoPorNumeroDocumento(Integer nroDocumento) {
        Optional<Empleado> empleado = repository.findByNroDocumento(nroDocumento);
        return empleado.isPresent();
    }

    public void validarNroDocumento(Empleado empleado) {
        if (esEmpleadoPorNumeroDocumento(empleado.getNroDocumento())) {
            throw new NroDocumentoInvalidoException(empleado.getNroDocumento());
        }
    }

    public boolean esEmpleadoPorEmail(String email) {
        Optional<Empleado> empleado = repository.findByEmail(email);
        return empleado.isPresent();
    }

    public boolean esEmailValido(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email != null && pattern.matcher(email).matches();
    }

    public void validarEmail(Empleado empleado) {
        if(esEmpleadoPorEmail(empleado.getEmail())) {
            throw new EmailRepetidoInvalidoException(empleado.getEmail());
        }
        if (!esEmailValido(empleado.getEmail())) {
            throw new FormatoEmailInvalidoException(empleado.getEmail());
        }
    }

    public void validarFechaNacimiento(Empleado empleado) {
        if(empleado.getFechaNacimiento().isAfter(fechaActual)) {
            throw new FechaIngresoInvalidaException(empleado.getFechaNacimiento());
        }
    }

    public void validarFechaIngreso(Empleado empleado) {
        if (empleado.getFechaIngreso().isAfter(fechaActual)) {
            throw new FechaIngresoInvalidaException(empleado.getFechaIngreso());
        }
    }

    public boolean esNombreValido(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return true;
        }

        String letrasRegex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(letrasRegex);
        return pattern.matcher(nombre).matches();
    }

    public boolean esApellidoValido(String apellido) {

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


    public void validarEmpleadoExistente(Long id) {
        Optional<Empleado> empleado = repository.findById(id);

        if (empleado.isEmpty()) {
            throw new IdEmpleadoInvalidoException(id);
        }
    }


}
