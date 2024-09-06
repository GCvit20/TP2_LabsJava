package TP2_LabsJava.repository;

import TP2_LabsJava.entity.ConceptoLaboral;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.entity.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IJornadaRepository extends JpaRepository<Jornada, Long> {

    List<Jornada> findByEmpleadoAndFecha(Empleado empleado, LocalDate fecha);
    List<Jornada> findByEmpleadoAndFechaBetween(Empleado empleado, LocalDate inicioSemana, LocalDate finSemana);
    List<Jornada> findByConceptoLaboralAndFecha(ConceptoLaboral conceptoLaboral, LocalDate fecha);
    List<Jornada> findByEmpleadoAndConceptoLaboralAndFecha(Empleado empleado, ConceptoLaboral conceptoLaboral, LocalDate fecha);

}
