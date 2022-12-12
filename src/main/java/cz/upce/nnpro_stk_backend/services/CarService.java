package cz.upce.nnpro_stk_backend.services;

import cz.upce.nnpro_stk_backend.dtos.CarDetailOutDto;
import cz.upce.nnpro_stk_backend.dtos.CarDto;
import cz.upce.nnpro_stk_backend.dtos.InspectionDto;
import cz.upce.nnpro_stk_backend.entities.Car;
import cz.upce.nnpro_stk_backend.dtos.CarFromCrvDto;
import cz.upce.nnpro_stk_backend.repositories.CarRepository;
import cz.upce.nnpro_stk_backend.repositories.InspectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final InspectionRepository inspectionRepository;
    public CarService(CarRepository carRepository, InspectionRepository inspectionRepository) {
        this.carRepository = carRepository;
        this.inspectionRepository = inspectionRepository;
    }

    public Car addCar(CarDto carDto){
        if(carRepository.existsBySpz(carDto.getSpz()))
            throw new IllegalArgumentException("The car already exists.");

        Car car = ConversionService.convertToCar(carDto);
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
        Car car = ConversionService.convertToCar(carDto);
        car.setId(carId);
        Car save = carRepository.save(car);
        return save;
    }

    public boolean isCarStolenBySpz(String spz) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            CarFromCrvDto result = restTemplate.getForObject("http://localhost:8081/car/getCarBySpz/" + spz, CarFromCrvDto.class);
           return result.isStolen();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isCarStolenByVin(String vin) {
        RestTemplate restTemplate = new RestTemplate();
        try {
        Boolean result = restTemplate.getForObject("http://localhost:8081/car/getCarByVin/" + vin, boolean.class);
        return result;
    }catch (Exception e){
        return false;
    }
    }




    public CarFromCrvDto getCarInfoFromCrvBySpz(String spz) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            CarFromCrvDto carFromCrvDto = restTemplate.getForObject("http://localhost:8081/car/getCarBySpz/" + spz, CarFromCrvDto.class);
            return carFromCrvDto;
        }catch (Exception e){
            return null;
        }
    }

    @PostConstruct
    public void init() throws Exception {
        LocalDate date = LocalDate.now();
        if (!carRepository.existsBySpz("1E1 1111")) {
            Car car = new Car();
            car.setVin("4Y1SL65848Z411439");
            car.setSpz("1E1 1111");
            car.setExpiryDateOfSTK(date.plusDays(20));
            carRepository.save(car);
        }
        if (!carRepository.existsBySpz("1E1 1112")) {
            Car car1 = new Car();
            car1.setVin("4Y1SL65848Z411439");
            car1.setSpz("1E1 1112");
            car1.setExpiryDateOfSTK(date.plusDays(20));
            carRepository.save(car1);
        }
    }

    public boolean isCarExistBySpz(String spz) {
        if(carRepository.existsBySpz(spz))
            return true;

        return false;
    }
}
