package TP2_LabsJava.exceptions;

import java.time.LocalDate;
import java.time.Period;

public class EdadInvalidaException extends RuntimeException {

    private LocalDate fechaNacimiento;


    public EdadInvalidaException(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public int getFechaNacimiento() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String getMessage() {
        return "La edad ingresada es de " + getFechaNacimiento() + ". La edad del empleado no puede ser menor a 18 años.";
    }
}
