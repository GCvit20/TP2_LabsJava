package TP2_LabsJava.exceptions;

public class EmpleadoNoEncontradoException extends RuntimeException {

    private Long id;

    public EmpleadoNoEncontradoException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return "No existe el empleado ingresado con id " + getId();
    }
}
