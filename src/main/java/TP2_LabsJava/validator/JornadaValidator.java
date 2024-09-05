package TP2_LabsJava.validator;

import TP2_LabsJava.entity.ConceptoLaboral;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.entity.Jornada;
import TP2_LabsJava.exceptions.HsTrabajadasNoObligatoriasException;
import TP2_LabsJava.exceptions.HsTrabajadasObligatoriasException;
import TP2_LabsJava.exceptions.RangoHorasException;
import TP2_LabsJava.repository.IJornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Component
public class JornadaValidator {

    @Autowired
    private IJornadaRepository jornadaRepository;

    public void validarCamposObligatorios(Jornada jornada) {

        if (jornada.getEmpleado() == null || jornada.getEmpleado().getId() == null) {
            throw new IllegalArgumentException("‘idEmpleado’ es obligatorio.");
        }
        if (jornada.getConceptoLaboral() == null || jornada.getConceptoLaboral().getId() == null) {
            throw new IllegalArgumentException("‘idConcepto’ es obligatorio.");
        }
        if (jornada.getFecha() == null) {
            throw new IllegalArgumentException("‘fecha’ es obligatorio.");
        }
    }

    public void validarHsTrabajadas(Jornada jornada, ConceptoLaboral conceptoLaboral) {

        if (jornada.getHorasTrabajadas() == null) {
            if ("Turno Extra".equals(conceptoLaboral.getNombre()) || "Turno Normal".equals(conceptoLaboral.getNombre())) {
                throw new HsTrabajadasObligatoriasException(conceptoLaboral.getNombre());
            }
        } else {
            if ("Día Libre".equals(conceptoLaboral.getNombre())) {
                throw new HsTrabajadasNoObligatoriasException(conceptoLaboral.getNombre());
            }
        }
    }

    public void validarRangoHorario(Jornada jornada, ConceptoLaboral conceptoLaboral) {

        if(jornada.getHorasTrabajadas() != null && (jornada.getHorasTrabajadas() < conceptoLaboral.getHsMinimo() || jornada.getHorasTrabajadas() > conceptoLaboral.getHsMaximo())) {
            throw new RangoHorasException(conceptoLaboral.getHsMinimo(), conceptoLaboral.getHsMaximo());
        }
    }

    public void validarCantidadHsPorJornada(Jornada jornada, Empleado empleado) {

        List<Jornada> jornadasDelDia = jornadaRepository.findByEmpleadoAndFecha(empleado, jornada.getFecha());

        int horasTotales = jornadasDelDia.stream()
                .mapToInt(j -> Optional.ofNullable(j.getHorasTrabajadas()).orElse(0))
                .sum() + Optional.ofNullable(jornada.getHorasTrabajadas()).orElse(0);

        if (horasTotales > 14) {
            throw new IllegalArgumentException("Un empleado no puede cargar más de 14 horas trabajadas en un día.");
        }
    }

    public void validarHorasSemanales(Jornada jornada, Empleado empleado) {

        LocalDate fecha = jornada.getFecha();
        LocalDate inicioSemana = fecha.with(java.time.DayOfWeek.MONDAY);
        LocalDate finSemana = inicioSemana.plusDays(6);

        List<Jornada> jornadasDeLaSemana = jornadaRepository.findByEmpleadoAndFechaBetween(empleado, inicioSemana, finSemana);

        int horasSemanales = jornadasDeLaSemana.stream()
                .mapToInt(j -> Optional.ofNullable(j.getHorasTrabajadas()).orElse(0))
                .sum() + Optional.ofNullable(jornada.getHorasTrabajadas()).orElse(0);

        if (horasSemanales > 52) {
            throw new IllegalArgumentException("El empleado ingresado supera las 52 horas semanales.");
        }
    }

    public void validarHorasMensuales(Jornada jornada, Empleado empleado) {
        LocalDate fecha = jornada.getFecha();
        LocalDate inicioMes = fecha.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate finMes = fecha.with(TemporalAdjusters.lastDayOfMonth());

        List<Jornada> jornadasDelMes = jornadaRepository.findByEmpleadoAndFechaBetween(empleado, inicioMes, finMes);

        int horasMensuales = jornadasDelMes.stream()
                .mapToInt(j -> Optional.ofNullable(j.getHorasTrabajadas()).orElse(0))
                .sum() + Optional.ofNullable(jornada.getHorasTrabajadas()).orElse(0);

        if (horasMensuales > 190) {
            throw new IllegalArgumentException("El empleado ingresado supera las 190 horas mensuales.");
        }
    }

}
