package cz.upce.nnpro_stk_backend.repositories;

import cz.upce.nnpro_stk_backend.entities.FaultOfInspection;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public interface FaultInspectionRepository  extends JpaRepository<FaultOfInspection, Long> {
    @EntityGraph(attributePaths = {"inspection","fault"})
    Set<FaultOfInspection> findByInspection_Id(Long id);

    @Query("select fi from FaultOfInspection fi where fi.fault.id =2  AND fi.inspection.id = 1 ")
    FaultOfInspection existsByCarAndEndOfSignUpIsNull(Long inspectionId, Long faultId);


}
