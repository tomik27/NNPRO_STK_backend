package cz.upce.nnpro_stk_backend.services;

import cz.upce.nnpro_stk_backend.dtos.CarDto;
import cz.upce.nnpro_stk_backend.entities.Car;
import cz.upce.nnpro_stk_backend.entities.Fault;
import cz.upce.nnpro_stk_backend.entities.Inspection;
import cz.upce.nnpro_stk_backend.repositories.CarRepository;
import cz.upce.nnpro_stk_backend.repositories.InspectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class CarService {
    private final CarRepository carRepository;
    private final InspectionRepository inspectionRepository;
    public CarService(CarRepository carRepository, InspectionRepository inspectionRepository) {
        this.carRepository = carRepository;
        this.inspectionRepository = inspectionRepository;
    }

    public Car addCar(CarDto carDto){
        Inspection inspection = inspectionRepository.findById(carDto.getInspectionId()).orElseThrow(() -> new NoSuchElementException("Inspection not found!"));
        Car car = ConversionService.convertToCar(carDto,inspection);
        Car save = carRepository.save(car);
        return save;
    }

    public Car removeCar(Long carId){
        Car car = carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException("Car not found!"));
        carRepository.deleteById(carId);
        return car;
    }

    public Car getCar(Long  carId){
        Car car = carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException("Car not found!"));
        return car;
    }

    public List<Car> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    public Car editCar(Long carId, CarDto carDto) {
        carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException("Car not found!"));
        Inspection inspection = inspectionRepository.findById(carDto.getInspectionId()).orElseThrow(() -> new NoSuchElementException("Inspection not found!"));
        Car car = ConversionService.convertToCar(carDto, inspection);
        car.setId(carId);
        Car save = carRepository.save(car);
        return save;
    }
}
