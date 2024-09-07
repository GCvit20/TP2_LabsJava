package TP2_LabsJava.exceptions;

import java.time.LocalDate;

public class FechaNacimientoInvalidaException extends RuntimeException {

    private LocalDate fechaNacimiento;
    private LocalDate fechaActual;

    public FechaNacimientoInvalidaException(LocalDate fechaNacimiento) {
        this.fechaActual = LocalDate.now();;
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaActual() {
        return fechaActual;
    }

    @Override
    public String getMessage() {
        return "La fecha de nacimiento (" + getFechaNacimiento() + ") no puede ser posterior al día de la fecha (" + getFechaActual() + ").";
    }
}
