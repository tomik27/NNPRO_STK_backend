package cz.upce.nnpro_stk_backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.nnpro_stk_backend.dtos.InspectionOutDto;
import cz.upce.nnpro_stk_backend.entities.BranchOffice;

import cz.upce.nnpro_stk_backend.entities.Car;
import cz.upce.nnpro_stk_backend.entities.Inspection;
import cz.upce.nnpro_stk_backend.entities.User;
import cz.upce.nnpro_stk_backend.dtos.BranchOfficeIdUserIdDto;
import cz.upce.nnpro_stk_backend.dtos.BranchOfficeInDto;
import cz.upce.nnpro_stk_backend.dtos.UserDetailOutDto;
import cz.upce.nnpro_stk_backend.repositories.BranchOfficeRepository;

import cz.upce.nnpro_stk_backend.repositories.CarRepository;
import cz.upce.nnpro_stk_backend.repositories.InspectionRepository;
import cz.upce.nnpro_stk_backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchOfficeService {
    private final BranchOfficeRepository branchOfficeRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final InspectionRepository inspectionRepository;
    private final ModelMapper modelMapper;

    public BranchOfficeService(BranchOfficeRepository branchOfficeRepository, UserRepository userRepository, CarRepository carRepository, InspectionRepository inspectionRepository, ModelMapper modelMapper) {
        this.branchOfficeRepository = branchOfficeRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.inspectionRepository = inspectionRepository;
        this.modelMapper = modelMapper;
    }

    public BranchOffice addOffice(BranchOfficeInDto branchOfficeDto) {
        if (branchOfficeRepository.existsByRegionAndDistrictAndCity(branchOfficeDto.getRegion(), branchOfficeDto.getDistrict(), branchOfficeDto.getCity())) {
            throw new IllegalArgumentException("The branch office already exists.");
        }
        BranchOffice branchOffice = new BranchOffice();
        branchOffice.setRegion(branchOfficeDto.getRegion());
        branchOffice.setDistrict(branchOfficeDto.getDistrict());
        branchOffice.setCity(branchOfficeDto.getCity());
        BranchOffice save = branchOfficeRepository.save(branchOffice);
        return save;
    }

    public BranchOffice removeOffice(Long officeId) {
        BranchOffice branchOffice = branchOfficeRepository.findById(officeId).orElseThrow(() -> new NoSuchElementException("Branch office not found!"));
        userRepository.setUserOfficeToNUllByOffice(branchOffice);
        branchOfficeRepository.deleteById(officeId);
        return branchOffice;
    }

    public BranchOffice editOffice(Long officeId, BranchOfficeInDto officeDto) {

        BranchOffice branchOffice = branchOfficeRepository.findById(officeId).orElseThrow(() -> new NoSuchElementException("Branch office not found!"));
        if (branchOfficeRepository.existsByRegionAndDistrictAndCityAndIdIsNot(officeDto.getRegion(), officeDto.getDistrict(), officeDto.getCity(), officeId)) {
            throw new IllegalArgumentException("The branch office already exists.");
        }
        branchOffice.setRegion(officeDto.getRegion());
        branchOffice.setDistrict(officeDto.getDistrict());
        branchOffice.setCity(officeDto.getCity());
        BranchOffice save = branchOfficeRepository.save(branchOffice);
        return save;
    }

    public BranchOffice getOffice(Long officeId) {
        BranchOffice branchOffice = branchOfficeRepository.findById(officeId).orElseThrow(() -> new NoSuchElementException("Branch office not found!"));
        return branchOffice;
    }

    public UserDetailOutDto addUserToOffice(BranchOfficeIdUserIdDto branchOfficeIdUserIdDto) {

        User user = userRepository.findById(branchOfficeIdUserIdDto.getUserId()).orElseThrow(() -> new NoSuchElementException("User not found!"));
        user.setBranchOffice(branchOfficeRepository.findById(branchOfficeIdUserIdDto.getBranchOfficeId()).orElseThrow(() -> new NoSuchElementException("Branch office not found!")));
        User save = userRepository.save(user);
        //todo: otestovat
        //        UserDetailOutDto detailOutDto = ConversionService.convertToUserDetailOutDto(save);
        UserDetailOutDto detailOutDto = modelMapper.map(save,UserDetailOutDto.class);
        return detailOutDto;
    }


   /* public UserDetailOutDto removeUserFromOffice(Long userId, Long branchId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found!"));
        BranchOffice branchOffice = branchOfficeRepository.findById(branchId).orElseThrow(() -> new NoSuchElementException("Branch office not found!");

        if(user.getBranchOffice().getId()!=branchOffice)

                user.setBranchOffice(branchOfficeRepository.findById(branchOfficeIdUserIdDto.getBranchOfficeId()).orElseThrow(() -> new NoSuchElementException("Branch office not found!")));
        User save = userRepository.save(user);
        //todo: otestovat
        //        UserDetailOutDto detailOutDto = ConversionService.convertToUserDetailOutDto(save);
        UserDetailOutDto detailOutDto = modelMapper.map(save,UserDetailOutDto.class);
        return detailOutDto;
    }*/

    public List<BranchOffice> getAllOffices() {
        List<BranchOffice> branchOffices = branchOfficeRepository.findAll();
        return branchOffices;
    }

    public String exportData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        List<User> allUsers = userRepository.findAll();

        List<Inspection> allInspections = inspectionRepository.findAll();
        List<Car> allCars = carRepository.findAll();
        //change allCars
        List<Car> newAllCars = new ArrayList<>();
        List<User> newAllUsers = new ArrayList<>();
        List<InspectionOutDto> newAllInspections = new ArrayList<>();
        allCars.forEach(car ->{
                    Car newCar = carRepository.findById(car.getId()).orElseThrow(() -> new NoSuchElementException("Car not found!"));
                    newCar.setInspections(null);

                    newAllCars.add(newCar);
        });
        allUsers.forEach(user ->{
            User newUser = userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("User not found!"));
            user.setInspections(null);
            user.setPassword(null);
            newAllUsers.add(newUser);
        });
        allInspections.forEach(inspection ->{
            Inspection newInspection = inspectionRepository.findById(inspection.getId()).orElseThrow(() -> new NoSuchElementException("Inspection not found!"));
            newInspection.setFaultOfInspections(null);
            InspectionOutDto inspectionOutDto = ConversionService.convertToInspectionOutDto(newInspection, null);
            newAllInspections.add(inspectionOutDto);
        });

        String jsonCars = mapper.writeValueAsString(newAllCars);
        String jsonUsers = mapper.writeValueAsString(newAllUsers);
        String jsonInspections = mapper.writeValueAsString(newAllInspections);
        String  body= "[]";

        return "{ \"cars\":"+jsonCars+",\"inspections\":" + jsonInspections +",\"users\":" + jsonUsers + "}";
    }

    public void importData(List<Car> cars, List<InspectionOutDto> inspectionOutDtos,List<User> users) {
       // List<User> users = new ArrayList<>();
        //Cars
        List<Car> filterCars = cars.stream().filter(car -> !carRepository.existsBySpz(car.getSpz())).collect(Collectors.toList());
        filterCars.forEach(car -> car.setId(null));

        //users
        List<User> filterUsers = users.stream().filter(user -> !userRepository.existsByUsername(user.getUsername())).collect(Collectors.toList());
        filterUsers.forEach(user -> {
            //todo change password policy
            user.setPassword("$2a$10$MQuBpeE5CbgERbKN7ecd1ea/Y3XwpfWVOqKFErLjbhT382.Rgviy.");
            user.setId(null);});
        //object for inspections
        User defaultUser = userRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("User not found!"));
        Car defaultCar = carRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("Car not found!"));
        BranchOffice defaultBranchOffice =branchOfficeRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("Branch not found!"));

        carRepository.saveAll(filterCars);
        userRepository.saveAll(filterUsers);



            List<Inspection> finalInspection = new ArrayList<>();
            List<InspectionOutDto> filterInspections = inspectionOutDtos.stream().filter(inspectionOutDto ->inspectionOutDto.getCarDto()!=null&&inspectionOutDto.getUserDto()!=null&& userRepository.existsByUsername(inspectionOutDto.getUserDto().getUsername()) && carRepository.existsBySpz(inspectionOutDto.getCarDto().getSpz())).collect(Collectors.toList());
        filterInspections.forEach(inspectionOutDto ->{

            List<User> pomUser = userRepository.findUserByUsername(inspectionOutDto.getUserDto().getUsername());
           User user =pomUser.get(0);
            Car car = carRepository.findCarBySpz(inspectionOutDto.getCarDto().getSpz());


            Inspection inspection = ConversionService.convertToInspection(inspectionOutDto, defaultBranchOffice, car, user);
           /* if (inspectionOutDto.getUser() == null)
                    inspectionOutDto.setUser(defaultUser);

                if (inspectionOutDto.getCar() == null)
                    inspectionOutDto.setCar(defaultCar);
                //todo dát do parametru branchID
                if (inspectionOutDto.getBranchOffice() == null)
                    inspectionOutDto.setBranchOffice(defaultBranchOffice);
                */
            finalInspection.add(inspection);

        });

        inspectionRepository.saveAll(finalInspection);
    }
}
