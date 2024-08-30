package TP2_LabsJava.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class EmpleadoValidator {

    public static boolean esEdadValida(LocalDate fechaNacimiento) {
        return fechaNacimiento != null && Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
}
