package cz.upce.nnpro_stk_backend.repositories;

import cz.upce.nnpro_stk_backend.entities.FaultOfInspection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FaultInspectionRepository  extends JpaRepository<FaultOfInspection, Long> {
    @EntityGraph(attributePaths = {"inspection","fault"})
    Set<FaultOfInspection> findByInspection_Id(Long id);
}
