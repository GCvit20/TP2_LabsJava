package TP2_LabsJava.globalExceptionHandler;

import TP2_LabsJava.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EdadInvalidaException.class)
    public ResponseEntity<String> handleEdadInvalidaException(EdadInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NroDocumentoInvalidoException.class)
    public ResponseEntity<String> handleNroDocumentoInvalidoException(NroDocumentoInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<String> handleEmailRepetidoInvalidoException(EmailDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(FechaIngresoInvalidaException.class)
    public ResponseEntity<String> handleFechaIngresoInvalidaException(FechaIngresoInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(FechaNacimientoInvalidaException.class)
    public ResponseEntity<String> handleFechaNacimientoInvalidaException(FechaNacimientoInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(FormatoEmailInvalidoException.class)
    public ResponseEntity<String> handleFormatoEmailInvalidoException(FormatoEmailInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(FormatoNombreInvalidoException.class)
    public ResponseEntity<String> handleFormatoNombreInvalidoException(FormatoNombreInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(FormatoApellidoInvalidoException.class)
    public ResponseEntity<String> handleFormatoApellidoInvalidoException(FormatoApellidoInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IdInexistenteException.class)
    public ResponseEntity<String> handleIdEmpleadoInvalidoException(IdInexistenteException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(HsTrabajadasObligatoriasException.class)
    public ResponseEntity<String> handleHsTrabajadasObligatoriasException(HsTrabajadasObligatoriasException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(HsTrabajadasNoObligatoriasException.class)
    public ResponseEntity<String> handleHsTrabajadasNoObligatoriasException(HsTrabajadasNoObligatoriasException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmpleadoNoEncontradoException.class)
    public ResponseEntity<String> handleEmpleadoNoEncontradoException(EmpleadoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ConceptoNoEncontradoException.class)
    public ResponseEntity<String> handleConceptoNoEncontradoException(ConceptoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RangoHorasException.class)
    public ResponseEntity<String> handleRangoHorasException(RangoHorasException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}
