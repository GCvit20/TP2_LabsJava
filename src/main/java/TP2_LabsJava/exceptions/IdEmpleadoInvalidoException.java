package TP2_LabsJava.exceptions;

public class IdEmpleadoInvalidoException extends RuntimeException {

    private Long id;

    public IdEmpleadoInvalidoException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return "No se encontró el empleado con Id: " + getId();
    }
}
