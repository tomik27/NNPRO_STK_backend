package cz.upce.nnpro_stk_backend.repositories;

import cz.upce.nnpro_stk_backend.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsBySpz(String username);
    Car findCarBySpz(String spz);

    List<Car> findCarsBySpz(String spz);
}
