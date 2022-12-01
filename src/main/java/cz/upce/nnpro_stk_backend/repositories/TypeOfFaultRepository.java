package cz.upce.nnpro_stk_backend.repositories;

import cz.upce.nnpro_stk_backend.entities.TypeOfFault;
import cz.upce.nnpro_stk_backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfFaultRepository extends JpaRepository<TypeOfFault, Long> {
}
