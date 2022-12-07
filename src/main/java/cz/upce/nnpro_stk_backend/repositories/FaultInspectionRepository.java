package cz.upce.nnpro_stk_backend.repositories;

import cz.upce.nnpro_stk_backend.entities.FaultOfInspection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface FaultInspectionRepository  extends JpaRepository<FaultOfInspection, Long> {
    @EntityGraph(attributePaths = {"inspection","fault"})
    Set<FaultOfInspection> findByInspection_Id(Long id);

    @Query("select fi from FaultOfInspection fi where  fi.fault.id =?2 and fi.inspection.id=?1")
    FaultOfInspection existsByInspectionAndFault(Long inspectionId, Long faultId);


}
