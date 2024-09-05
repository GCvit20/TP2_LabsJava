package TP2_LabsJava.exceptions;

public class RangoHorasException extends RuntimeException {

    private Integer hsMinima;
    private Integer hsMaxima;

    public RangoHorasException(Integer hsMinima, Integer hsMaxima) {
        this.hsMinima = hsMinima;
        this.hsMaxima = hsMaxima;
    }

    public Integer getHsMinima() {
        return hsMinima;
    }

    public Integer getHsMaxima() {
        return hsMaxima;
    }

    @Override
    public String getMessage() {
        return "El rango de horas que se puede cargar para este concepto es de " + getHsMinima()+ "-" + getHsMaxima();
    }
}
