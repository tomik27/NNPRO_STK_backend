package cz.upce.nnpro_stk_backend.repositories;

import cz.upce.nnpro_stk_backend.entities.Fault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaultRepository extends JpaRepository<Fault, Long> {
    boolean existsByDescription(String description);
}
