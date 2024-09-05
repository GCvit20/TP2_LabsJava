package TP2_LabsJava.exceptions;

public class HsTrabajadasNoObligatoriasException extends RuntimeException {

    private String conceptoLaboral;

    public HsTrabajadasNoObligatoriasException(String conceptoLaboral) {
        this.conceptoLaboral = conceptoLaboral;
    }

    public String getConceptoLaboral() {
        return conceptoLaboral;
    }

    @Override
    public String getMessage() {
        return "El concepto ingresado ("+ getConceptoLaboral() + ")" + " no requiere el ingreso de ‘hsTrabajadas’";
    }
}
