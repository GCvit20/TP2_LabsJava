package TP2_LabsJava.exceptions;

public class ConceptoNoEncontradoException extends RuntimeException {

    private String conceptoNombre;

    public ConceptoNoEncontradoException(String conceptoNombre) {
        this.conceptoNombre = conceptoNombre;
    }

    public String getConceptoNombre() {
        return conceptoNombre;
    }

    @Override
    public String getMessage() {
        return "No existe el concepto ingresado (" + getConceptoNombre() + ")";
    }
}
