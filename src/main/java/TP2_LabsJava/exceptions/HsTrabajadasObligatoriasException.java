package TP2_LabsJava.exceptions;

public class HsTrabajadasObligatoriasException extends RuntimeException {

    private String conceptoLaboral;

    public HsTrabajadasObligatoriasException(String conceptoLaboral) {
        this.conceptoLaboral = conceptoLaboral;
    }

    public String getConceptoLaboral() {
        return conceptoLaboral;
    }

    @Override
    public String getMessage() {
        return "'hsTrabajadas' es obligatorio para el concepto ingresado (" + getConceptoLaboral() + ")";
    }
}
