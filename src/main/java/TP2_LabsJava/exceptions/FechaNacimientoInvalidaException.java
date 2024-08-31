package TP2_LabsJava.exceptions;

import java.time.LocalDate;

public class FechaNacimientoInvalidaException extends RuntimeException {

    private LocalDate fechaNacimiento;
    private LocalDate fechaActual;

    public FechaNacimientoInvalidaException(LocalDate fechaActual) {
        this.fechaActual = fechaActual;
        this.fechaNacimiento = LocalDate.now();
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
