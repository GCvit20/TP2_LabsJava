package TP2_LabsJava.exceptions;

public class EmailDuplicadoException extends RuntimeException {

  private String email;

  public EmailDuplicadoException(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getMessage() {
    return "Ya existe un empleado con el email ingresado (" + getEmail() + ")";
  }
}
