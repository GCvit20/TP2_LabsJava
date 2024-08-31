package TP2_LabsJava.exceptions;

public class NroDocumentoInvalidoException extends RuntimeException {

    private Integer nroDocumento;

    public NroDocumentoInvalidoException(Integer nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public Integer getNroDocumento() {
        return nroDocumento;
    }

    @Override
    public String getMessage() {
        return "Ya existe un empleado con el documento ingresado (" + getNroDocumento() + ")";
    }
}
