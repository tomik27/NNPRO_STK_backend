package cz.upce.nnpro_stk_backend.repositories;

import cz.upce.nnpro_stk_backend.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsBySpz(String username);
    Car findCarBySpz(String spz);
}
