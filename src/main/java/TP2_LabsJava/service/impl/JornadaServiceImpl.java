package TP2_LabsJava.service.impl;


import TP2_LabsJava.dto.JornadaDTO;
import TP2_LabsJava.dto.ResponseDTO;
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
    public ResponseDTO agregarJornada(JornadaDTO jornadaDTO) {

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
        jornadaValidator.validarMaximoTurnos(jornada);
        jornadaValidator.validarDiasLibres(jornada, empleado);
        jornadaValidator.validarDiasLibresPorMes(jornada, empleado);
        jornadaValidator.validarNumeroEmpleados(jornada, conceptoLaboral);
        jornadaValidator.validarJornadaUnicaPorEmpleado(jornada, empleado, conceptoLaboral);

        Jornada savedJornada = jornadaRepository.save(jornada);
        return ResponseDTO.from(savedJornada, empleado, conceptoLaboral.getNombre());
    }

}
