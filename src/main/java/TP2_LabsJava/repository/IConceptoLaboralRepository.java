package TP2_LabsJava.repository;


import TP2_LabsJava.entity.ConceptoLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IConceptoLaboralRepository extends JpaRepository<ConceptoLaboral, Long> {

    List<ConceptoLaboral> findByNombreContaining(String nombre);
    List<ConceptoLaboral> findByIdAndNombreContaining(Long id, String nombre);
    ConceptoLaboral findByNombre(String conceptoNombre);
}
