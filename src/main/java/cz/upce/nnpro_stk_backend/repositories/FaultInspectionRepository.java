package cz.upce.nnpro_stk_backend.repositories;

import cz.upce.nnpro_stk_backend.entities.FaultOfInspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaultInspectionRepository  extends JpaRepository<FaultOfInspection, Long> {
}
