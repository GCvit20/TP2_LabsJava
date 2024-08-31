package TP2_LabsJava.exceptions;

public class FormatoNombreInvalidoException extends RuntimeException {

    String nombre;

    public FormatoNombreInvalidoException(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getMessage() {
        return "Solo se permiten letras en el campo ‘nombre’.";
    }
}
