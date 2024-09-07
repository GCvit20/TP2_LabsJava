package TP2_LabsJava.service.impl;


import TP2_LabsJava.dto.JornadaDTO;
import TP2_LabsJava.dto.JornadaResponse;
import TP2_LabsJava.entity.ConceptoLaboral;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.entity.Jornada;
import TP2_LabsJava.exceptions.ConceptoNoEncontradoException;
import TP2_LabsJava.exceptions.EmpleadoNoEncontradoException;
import TP2_LabsJava.repository.IConceptoLaboralRepository;
import TP2_LabsJava.repository.IEmpleadoRepository;
import TP2_LabsJava.repository.IJornadaRepository;
import TP2_LabsJava.service.IJornadaService;
import TP2_LabsJava.validator.JornadaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JornadaServiceImpl implements IJornadaService {

    @Autowired
    private IJornadaRepository jornadaRepository;

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Autowired
    private IConceptoLaboralRepository conceptoLaboralRepository;

    @Autowired
    JornadaValidator jornadaValidator;

    @Override
    public JornadaResponse agregarJornada(JornadaDTO jornadaDTO) {

        Jornada jornada = jornadaDTO.toEntity();
        jornadaValidator.validarCamposObligatorios(jornada);

        Empleado empleado = empleadoRepository.findById(jornada.getEmpleado().getId())
                .orElseThrow(() -> new EmpleadoNoEncontradoException(jornada.getEmpleado().getId()));

        ConceptoLaboral conceptoLaboral = conceptoLaboralRepository.findById(jornada.getConceptoLaboral().getId())
                .orElseThrow(() -> new ConceptoNoEncontradoException(jornada.getConceptoLaboral().getNombre()));

        jornadaValidator.validarHsTrabajadas(jornada, conceptoLaboral);
        jornadaValidator.validarRangoHorario(jornada, conceptoLaboral);
        jornadaValidator.validarCantidadHsPorJornada(jornada, empleado);
        jornadaValidator.validarHorasSemanales(jornada, empleado);
        jornadaValidator.validarHorasMensuales(jornada, empleado);
        jornadaValidator.validarDiaLibreEnFecha(jornada, empleado, conceptoLaboral);
        jornadaValidator.validarMaximoTurnos(jornada, empleado, conceptoLaboral);
        jornadaValidator.validarDiasLibres(jornada, empleado);
        jornadaValidator.validarDiasLibresPorMes(jornada, empleado);
        jornadaValidator.validarNumeroEmpleados(jornada, conceptoLaboral);
        jornadaValidator.validarJornadaUnicaPorEmpleado(jornada, empleado, conceptoLaboral);

        Jornada savedJornada = jornadaRepository.save(jornada);
        return JornadaResponse.from(savedJornada, empleado, conceptoLaboral.getNombre());
    }

    @Override
    public List<JornadaResponse> obtenerJornada(String fechaDesde, String fechaHasta, String nroDocumento) {

        LocalDate fechaDesdeParsed = jornadaValidator.validarFecha(fechaDesde, "fechaDesde");
        LocalDate fechaHastaParsed = jornadaValidator.validarFecha(fechaHasta, "fechaHasta");
        Integer nroDocumentoParsed = jornadaValidator.validarNroDocumento(nroDocumento);

        List<Jornada> jornadas = filtrarPorFechas(fechaDesdeParsed, fechaHastaParsed);
        jornadas = filtrarPorNroDocumento(jornadas, nroDocumentoParsed);
        return convertirEnDTO(jornadas);
    }

    private List<Jornada> filtrarPorFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        if (fechaDesde != null && fechaHasta != null) {
            if (fechaDesde.isAfter(fechaHasta)) {
                throw new IllegalArgumentException("El campo ‘fechaDesde’ no puede ser mayor que ‘fechaHasta’.");
            }
            return jornadaRepository.findByFechaBetween(fechaDesde, fechaHasta);
        } else if (fechaDesde != null) {
            return jornadaRepository.findByFechaAfter(fechaDesde);
        } else if (fechaHasta != null) {
            return jornadaRepository.findByFechaBefore(fechaHasta);
        } else {
            return jornadaRepository.findAll();
        }
    }

    private List<Jornada> filtrarPorNroDocumento(List<Jornada> jornadas, Integer nroDocumento) {
        if (nroDocumento != null) {
            return jornadas.stream()
                    .filter(jornada -> jornada.getEmpleado().getNroDocumento().equals(nroDocumento))
                    .collect(Collectors.toList());
        }
        return jornadas;
    }

    private List<JornadaResponse> convertirEnDTO(List<Jornada> jornadas) {
        return jornadas.stream().map(jornada -> {
            Empleado empleado = jornada.getEmpleado();
            String conceptoLaboral = jornada.getConceptoLaboral().getNombre();
            return JornadaResponse.from(jornada, empleado, conceptoLaboral);
        }).collect(Collectors.toList());
    }





}
