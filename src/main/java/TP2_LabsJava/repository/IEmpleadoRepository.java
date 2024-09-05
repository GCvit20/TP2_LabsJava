package TP2_LabsJava.repository;

import TP2_LabsJava.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByNroDocumento(Integer nroDocumento);
    Optional<Empleado> findByEmail(String nroDocumento);
    boolean existsByNroDocumentoAndIdNot(Integer nroDocumento, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);
}
