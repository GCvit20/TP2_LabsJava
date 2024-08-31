package TP2_LabsJava.exceptions;

public class FormatoEmailInvalidoException extends RuntimeException {

    private String email;

    public FormatoEmailInvalidoException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getMessage() {
        return "El email ingresado no es correcto (" + getEmail() + ").";
    }
}
