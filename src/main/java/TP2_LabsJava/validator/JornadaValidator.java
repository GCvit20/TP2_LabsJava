package TP2_LabsJava.validator;

import TP2_LabsJava.entity.ConceptoLaboral;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.entity.Jornada;
import TP2_LabsJava.exceptions.HsTrabajadasNoObligatoriasException;
import TP2_LabsJava.exceptions.HsTrabajadasObligatoriasException;
import TP2_LabsJava.exceptions.RangoHorasException;
import TP2_LabsJava.repository.IConceptoLaboralRepository;
import TP2_LabsJava.repository.IEmpleadoRepository;
import TP2_LabsJava.repository.IJornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Component
public class JornadaValidator {

    @Autowired
    private IJornadaRepository jornadaRepository;

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Autowired
    private IConceptoLaboralRepository conceptoLaboralRepository;

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

    public void validarDiaLibreEnFecha(Jornada jornada, Empleado empleado, ConceptoLaboral conceptoLaboral) {

        List<Jornada> jornadasDelDia = jornadaRepository.findByEmpleadoAndFecha(empleado, jornada.getFecha());

        boolean existeDiaLibre = jornadasDelDia.stream()
                .anyMatch(j -> "Día Libre".equals(j.getConceptoLaboral().getNombre()));

        boolean existeTurnoNormal = jornadasDelDia.stream()
                .anyMatch(j -> "Turno Normal".equals(j.getConceptoLaboral().getNombre()));

        boolean existeTurnoExtra = jornadasDelDia.stream()
                .anyMatch(j -> "Turno Extra".equals(j.getConceptoLaboral().getNombre()));

        if (existeDiaLibre) {
            throw new IllegalArgumentException("El empleado ingresado cuenta con un día libre en esa fecha.");
        } else if (Objects.equals(conceptoLaboral.getNombre(), "Día Libre") && (existeTurnoNormal || existeTurnoExtra)) {
            throw new IllegalArgumentException("El empleado ya cuenta con un turno asignado en esta fecha.");
        }
    }

    public void validarMaximoTurnos(Jornada jornada) {
        Empleado empleado = jornada.getEmpleado();
        LocalDate fecha = jornada.getFecha();


        LocalDate inicioSemana = fecha.with(DayOfWeek.MONDAY);
        LocalDate finSemana = fecha.with(DayOfWeek.SUNDAY);

        List<Jornada> jornadasDeLaSemana = jornadaRepository.findByEmpleadoAndFechaBetween(empleado, inicioSemana, finSemana);

        long cantidadTurnosExtra = jornadasDeLaSemana.stream()
                .filter(nuevaJornada -> nuevaJornada.getConceptoLaboral().getNombre().equalsIgnoreCase("Turno Extra"))
                .count();

        long cantidadTurnosNormales = jornadasDeLaSemana.stream()
                .filter(nuevaJornada -> nuevaJornada.getConceptoLaboral().getNombre().equalsIgnoreCase("Turno Normal"))
                .count();


        if (cantidadTurnosExtra >= 3) {
            throw new IllegalArgumentException("El empleado ingresado ya cuenta con 3 turnos extra esta semana.");
        }

        if (cantidadTurnosNormales >= 5) {
            throw new IllegalArgumentException("El empleado ingresado ya cuenta con 5 turnos normales esta semana.");
        }
    }

    public void validarDiasLibres(Jornada jornada, Empleado empleado) {

        LocalDate fecha = jornada.getFecha();
        LocalDate inicioSemana = fecha.with(java.time.DayOfWeek.MONDAY);
        LocalDate finSemana = inicioSemana.plusDays(6);

        List<Jornada> jornadas = jornadaRepository.findByEmpleadoAndFechaBetween(empleado, inicioSemana, finSemana);

        long diasLibres = jornadas.stream()
                .filter(j -> j.getConceptoLaboral().getNombre().equals("Día Libre"))
                .count();

        if (diasLibres > 1) {
            throw new IllegalArgumentException("El empleado tiene más de 2 días libres en la semana.");
        }
    }

    public void validarDiasLibresPorMes(Jornada jornada, Empleado empleado) {

        LocalDate fecha = jornada.getFecha();
        LocalDate inicioMes = fecha.withDayOfMonth(1);
        LocalDate finMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());

        List<Jornada> jornadas = jornadaRepository.findByEmpleadoAndFechaBetween(empleado, inicioMes, finMes);

        long diasLibres = jornadas.stream()
                .filter(j -> j.getConceptoLaboral().getNombre().equals("Día Libre"))
                .count();


        if (diasLibres > 5) {
            throw new IllegalArgumentException("El empleado tiene más de 5 días libres en el mes.");
        }
    }

    public void validarNumeroEmpleados(Jornada jornada, ConceptoLaboral conceptoLaboral) {

        LocalDate fecha = jornada.getFecha();

        ConceptoLaboral concepto = conceptoLaboralRepository.findByNombre(conceptoLaboral.getNombre());
        List<Jornada> jornadas = jornadaRepository.findByConceptoLaboralAndFecha(concepto, fecha);

        Set<Empleado> empleadosUnicos = new HashSet<>();

        for (Jornada j : jornadas) {
            Empleado emp = j.getEmpleado();
            if (emp != null) {
                empleadosUnicos.add(emp);
            }
        }

        long empleadosRegistrados = empleadosUnicos.size();

        if (empleadosRegistrados >= 2) {
            throw new IllegalArgumentException("Ya existen 2 empleados registrados para este concepto en la fecha ingresada.");
        }
    }

    public void validarJornadaUnicaPorEmpleado(Jornada jornada, Empleado empleado, ConceptoLaboral conceptoLaboral) {

        LocalDate fecha = jornada.getFecha();

        ConceptoLaboral concepto = conceptoLaboralRepository.findByNombre(conceptoLaboral.getNombre());

        List<Jornada> jornadas = jornadaRepository.findByEmpleadoAndConceptoLaboralAndFecha(empleado, concepto, fecha);

        if (!jornadas.isEmpty()) {
            throw new IllegalArgumentException("El empleado ya tiene registrado una jornada con este concepto en la fecha ingresada.");
        }
    }

    public LocalDate validarFecha(String fecha, String nombreCampo) {

        if (fecha != null && !fecha.isEmpty()) {
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(fecha, formato);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(String.format("El campo '%s' debe respetar el formato yyyy-MM-dd.", nombreCampo));
            }
        }
        return null;
    }

    public Integer validarNroDocumento(String nroDocumento) {
        if (nroDocumento != null && !nroDocumento.isEmpty()) {
            try {
                return Integer.parseInt(nroDocumento);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("El campo 'nroDocumento' solo puede contener números enteros.");
            }
        }
        return null;
    }

}
