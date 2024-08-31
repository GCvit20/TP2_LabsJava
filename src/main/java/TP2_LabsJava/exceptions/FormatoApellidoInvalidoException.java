package TP2_LabsJava.exceptions;

public class FormatoApellidoInvalidoException extends RuntimeException {

    String apellido;

    public FormatoApellidoInvalidoException(String nombre) {
        this.apellido = apellido;
    }


    @Override
    public String getMessage() {
        return "Solo se permiten letras en el campo ‘apellido’.";
    }
}
