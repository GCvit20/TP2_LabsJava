package TP2_LabsJava.repository;

import TP2_LabsJava.entity.ConceptoLaboral;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.entity.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface IJornadaRepository extends JpaRepository<Jornada, Long> {

    List<Jornada> findByEmpleadoAndFecha(Empleado empleado, LocalDate fecha);
    List<Jornada> findByEmpleadoAndFechaBetween(Empleado empleado, LocalDate inicioSemana, LocalDate finSemana);
    List<Jornada> findByConceptoLaboralAndFecha(ConceptoLaboral conceptoLaboral, LocalDate fecha);
    List<Jornada> findByEmpleadoAndConceptoLaboralAndFecha(Empleado empleado, ConceptoLaboral conceptoLaboral, LocalDate fecha);
    List<Jornada> findByFechaBetween(LocalDate fechaDesde, LocalDate fechaHasta);
    List<Jornada> findByFechaAfter(LocalDate fechaDesde);
    List<Jornada> findByFechaBefore(LocalDate fechaDesde);
    boolean existsByEmpleadoId(Long empleadoId);

}
