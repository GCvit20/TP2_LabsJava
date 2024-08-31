package TP2_LabsJava.exceptions;

import java.time.LocalDate;

public class FechaIngresoInvalidaException extends RuntimeException {

    private LocalDate fechaIngreso;
    private LocalDate fechaActual;

    public FechaIngresoInvalidaException(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
        this.fechaActual = LocalDate.now();
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDate getFechaActual() {
        return fechaActual;
    }

    @Override
    public String getMessage() {
        return "La fecha de ingreso (" + getFechaIngreso() + ") no puede ser posterior al día de la fecha (" + getFechaActual() + ").";
    }
}
