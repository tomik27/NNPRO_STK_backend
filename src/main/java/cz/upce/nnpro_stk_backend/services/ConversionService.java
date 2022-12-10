package cz.upce.nnpro_stk_backend.services;

import cz.upce.nnpro_stk_backend.entities.*;
import cz.upce.nnpro_stk_backend.dtos.*;

import java.util.*;

public class ConversionService {
    public static UserDetailOutDto convertToUserDetailOutDto(User user) {
        UserDetailOutDto userDto = new UserDetailOutDto();
        userDto.setId(user.getId());
        userDto.setInspections(user.getInspections());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setJobPosition(user.getJobPosition());
        userDto.setRole(user.getRole());
        userDto.setDeclarationOfTax(user.getDeclarationOfTax());
        userDto.setHourRate(user.getHourRate());
        userDto.setPassword(user.getPassword());
        userDto.setNumberOfChildren(user.getNumberOfChildren());
        if (user.getBranchOffice() != null) {
            BranchOfficeDto branchOfficeDto = new BranchOfficeDto();
            branchOfficeDto.setId(user.getBranchOffice().getId());
            branchOfficeDto.setRegion(user.getBranchOffice().getRegion());
            branchOfficeDto.setDistrict(user.getBranchOffice().getDistrict());
            userDto.setBranchOfficeDto(branchOfficeDto);
        }

        return userDto;
    }
/*
    public static Car convertToCar(CreateCarDto createCarDto, String spz) {
        Car newCar = new Car();
        newCar.setVin(createCarDto.getVin());
        newCar.setSPZ(spz);
        newCar.setColor(createCarDto.getColor());
        newCar.setEnginePower(createCarDto.getEnginePower());
        newCar.setInDeposit(createCarDto.isInDeposit());
        newCar.setManufacturer(createCarDto.getManufacturer());
        newCar.setType(createCarDto.getType());
        newCar.setEmissionStandard(createCarDto.getEmissionStandard());
        newCar.setYearOfCreation(createCarDto.getYearOfCreation());
        return newCar;
    }

    public static Car convertToCar(CreateCarDto createCarDto, Car newCar) {
        newCar.setVin(createCarDto.getVin());
        newCar.setColor(createCarDto.getColor());
        newCar.setEnginePower(createCarDto.getEnginePower());
        newCar.setInDeposit(createCarDto.isInDeposit());
        newCar.setManufacturer(createCarDto.getManufacturer());
        newCar.setType(createCarDto.getType());
        newCar.setEmissionStandard(createCarDto.getEmissionStandard());
        newCar.setYearOfCreation(createCarDto.getYearOfCreation());
        return newCar;
    }

    public static CarDetailOutDto convertToCarDetailOutDto(Car car) {
        CarDetailOutDto carDetailOutDto = new CarDetailOutDto();
        carDetailOutDto.setId(car.getId());
        carDetailOutDto.setVin(car.getVin());
        carDetailOutDto.setSPZ(car.getSPZ());
        carDetailOutDto.setColor(car.getColor());
        carDetailOutDto.setEnginePower(car.getEnginePower());
        carDetailOutDto.setTorque(car.getTorque());
        carDetailOutDto.setInDeposit(car.isInDeposit());
        carDetailOutDto.setManufacturer(car.getManufacturer());
        carDetailOutDto.setType(car.getType());
        carDetailOutDto.setEmissionStandard(car.getEmissionStandard());
        carDetailOutDto.setYearOfCreation(car.getYearOfCreation());
        List<OwnerInCarDto> owners = new ArrayList<>();
        for (CarOwner carOwner : car.getCarOwners().stream().toList()) {
            OwnerInCarDto ownerDto = new OwnerInCarDto();
            ownerDto.setFirstName(carOwner.getOwner().getFirstName());
            ownerDto.setLastName(carOwner.getOwner().getLastName());
            ownerDto.setCity(carOwner.getOwner().getCity());
            ownerDto.setStreet(carOwner.getOwner().getStreet());
            ownerDto.setNumberOfHouse(carOwner.getOwner().getNumberOfHouse());
            ownerDto.setZipCode(carOwner.getOwner().getZipCode());
            ownerDto.setBirthDate(carOwner.getOwner().getBirthDate());
            ownerDto.setId(carOwner.getOwner().getId());
            ownerDto.setStartOfSignUp(carOwner.getStartOfSignUp());
            ownerDto.setEndOfSignUp(carOwner.getEndOfSignUp());
            owners.add(ownerDto);
        }
        carDetailOutDto.setOwners(owners);
        if (car.getBranchOffice() != null) {
            BranchOfficeDto branchOfficeDto = new BranchOfficeDto();
            branchOfficeDto.setId(car.getBranchOffice().getId());
            branchOfficeDto.setDistrict(car.getBranchOffice().getDistrict());
            branchOfficeDto.setRegion(car.getBranchOffice().getRegion());
            carDetailOutDto.setBranchOffice(branchOfficeDto);
        }

        return carDetailOutDto;
    }

    public static Owner convertToOwner(OwnerDto ownerDto) {
        Owner newOwner = new Owner();
        newOwner.setFirstName(ownerDto.getFirstName());
        newOwner.setLastName(ownerDto.getLastName());
        newOwner.setBirthDate(ownerDto.getBirthDate());
        newOwner.setCity(ownerDto.getCity());
        newOwner.setStreet(ownerDto.getStreet());
        newOwner.setZipCode(ownerDto.getZipCode());
        newOwner.setNumberOfHouse(ownerDto.getNumberOfHouse());
        return newOwner;
    }

    public static Owner convertToOwner(Owner newOwner, OwnerDto ownerDto) {

        newOwner.setFirstName(ownerDto.getFirstName());
        newOwner.setLastName(ownerDto.getLastName());
        newOwner.setBirthDate(ownerDto.getBirthDate());
        newOwner.setCity(ownerDto.getCity());
        newOwner.setStreet(ownerDto.getStreet());
        newOwner.setZipCode(ownerDto.getZipCode());
        newOwner.setNumberOfHouse(ownerDto.getNumberOfHouse());
        return newOwner;
    }*/

    public static User convertToUser(UserDto userDto, Optional<Role> role) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setJobPosition(userDto.getJobPosition());
        user.setHourRate(userDto.getHourRate());
        user.setNumberOfChildren(userDto.getNumberOfChildren());
        user.setDeclarationOfTax(userDto.getDeclarationOfTax());

        role.ifPresent(user::setRole);
        return user;
    }

    public static User convertToUser(UserDto userDto, User user, Optional<Role> role) {
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setDeclarationOfTax(userDto.getDeclarationOfTax());
        user.setHourRate(userDto.getHourRate());
        user.setNumberOfChildren(userDto.getNumberOfChildren());
        user.setJobPosition(userDto.getJobPosition());
        role.ifPresent(user::setRole);
        return user;
    }

    public static Fault convertToFault(FaultDto faultDto, Optional<TypeOfFault> typeOfFault){
        Fault fault= new Fault();
        fault.setDescription(faultDto.getDescription());
        typeOfFault.ifPresent(fault::setTypeOfFault);
        return fault;
    }

    public static Fault convertToFault(FaultDto faultDto, TypeOfFault typeOfFault){
        Fault fault= new Fault();
        fault.setDescription(faultDto.getDescription());
        fault.setTypeOfFault(typeOfFault);
        return fault;
    }

    public static Inspection convertToInspection(InspectionInDto inspectionInDto, User user, BranchOffice branchOffice) {
        Inspection inspection = new Inspection();
        inspection.setInspectionTime(inspectionInDto.getInspectionTime());
        inspection.setBranchOffice(branchOffice);
        inspection.setDate(inspectionInDto.getDate());
        inspection.setUser(user);
        return inspection;
    }

    public static List<FaultOfInspectionDto> convertToListOfFaultDto(Set<FaultOfInspection> setFaults) {
        List<FaultOfInspectionDto> listFaultsDto = new ArrayList<>();
        for (FaultOfInspection faultOfInspection : setFaults) {
            FaultOfInspectionDto faultOfInspectionDto=new FaultOfInspectionDto();
            faultOfInspectionDto.setFaultId(faultOfInspection.getFault().getId());
            faultOfInspectionDto.setInspectionId(faultOfInspection.getInspection().getId());
            faultOfInspectionDto.setFaultDescription(faultOfInspection.getFault().getDescription());
            //todo zmenit na id typOfFault
            faultOfInspectionDto.setTypeOfFault(faultOfInspection.getFault().getTypeOfFault().getDescription());
            listFaultsDto.add(faultOfInspectionDto);
        }
        return listFaultsDto;
    }

    public static Inspection  convertToInspection(InspectionOutDto inspectionOutDto,BranchOffice branchOffice, Car car,User user){
        Inspection inspection = new Inspection();
        inspection.setInspectionTime(inspectionOutDto.getInspectionTime());
        inspection.setDate(inspectionOutDto.getDate());
        inspection.setUser(user);
        inspection.setBranchOffice(branchOffice);
        inspection.setCar(car);
        return inspection;
    }

    public static InspectionOutDto convertToInspectionOutDto(Inspection inspection, List<FaultOfInspectionDto> faultOfInspectionDtos) {
        InspectionOutDto inspectionOutDto = new InspectionOutDto();
        inspectionOutDto.setInspectionTime(inspection.getInspectionTime());
        inspectionOutDto.setFaultsOfInspectionList(faultOfInspectionDtos);
        inspectionOutDto.setDate(inspection.getDate());

        if (inspection.getBranchOffice() != null) {
            BranchOfficeDto branchOfficeDto = new BranchOfficeDto();
            branchOfficeDto.setId(inspection.getBranchOffice().getId());
            branchOfficeDto.setRegion(inspection.getBranchOffice().getRegion());
            branchOfficeDto.setDistrict(inspection.getBranchOffice().getDistrict());
            branchOfficeDto.setCity(inspection.getBranchOffice().getCity());
            inspectionOutDto.setBranchDto(branchOfficeDto);
        }
        if(inspection.getUser()!=null){
            UserDto userDto = new UserDto();
            userDto.setDeclarationOfTax(inspection.getUser().getDeclarationOfTax());
            userDto.setEmail(inspection.getUser().getEmail());
            userDto.setUsername(inspection.getUser().getUsername());
            userDto.setHourRate(inspection.getUser().getHourRate());
            userDto.setNumberOfChildren(inspection.getUser().getNumberOfChildren());
            userDto.setRole(inspection.getUser().getRole().getId());
            userDto.setJobPosition(inspection.getUser().getJobPosition());
            inspectionOutDto.setUserDto(userDto);
        }
        if(inspection.getCar()!=null){
            CarDto carDto= new CarDto();
            carDto.setExpiryDateOfSTK(inspection.getCar().getExpiryDateOfSTK());
            carDto.setOperable(inspection.getCar().getOperable());
            carDto.setSpz(inspection.getCar().getSpz());
            carDto.setVin(inspection.getCar().getVin());
            inspectionOutDto.setCarDto(carDto);
        }
        return inspectionOutDto;
    }

    public static Car convertToCar(CarDto carDto) {
        Car car = new Car();
        car.setOperable(carDto.getOperable());
        car.setSpz(carDto.getSpz());
        car.setVin(carDto.getVin());
     /*   Set<Inspection> inspections =new HashSet<>(Arrays.asList(inspection));
        car.setInspections(inspections);*/
        car.setExpiryDateOfSTK(carDto.getExpiryDateOfSTK());
        return car;
    }

    public static Inspection convertToInspectionWithFaults(InspectionWithFaultsInDto inspectionInDto, User user, BranchOffice branchOffice,Car car) {
        Inspection inspection = new Inspection();
        inspection.setInspectionTime(inspectionInDto.getInspectionTime());
        inspection.setBranchOffice(branchOffice);
        inspection.setDate(inspectionInDto.getDate());
        inspection.setCar(car);
        inspection.setUser(user);
        return inspection;
    }

    public static UserWageDto convertToUserWageDto(User user, int salary, int numberOfInspection, int hoursWorked) {
        UserWageDto userWageDto = new UserWageDto();
        userWageDto.setMonthSalary(salary);
        userWageDto.setNumberOfInspection(numberOfInspection);
        userWageDto.setHourRate(user.getHourRate());
        userWageDto.setNumberOfChildren(user.getNumberOfChildren());
        userWageDto.setDeclarationOfTax(user.getDeclarationOfTax());
        userWageDto.setRole(user.getRole());
        userWageDto.setNumberOfHoursWorked(hoursWorked);
        userWageDto.setUsername(user.getUsername());

        return userWageDto;
    }

    /*public CarDto convertToCarOutDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setVin(car.getVin());
        carDto.setSpz(car.getVin());
        carDto.setOperable(car.getOperable());
        carDto.setExpiryDateOfSTK(car.getExpiryDateOfSTK());
        if(car.ge)
    }*/

//    public static OwnerDetailOutDto convertToOwnerDetailOutDto(Owner owner, Page<Car> carPage) {
//        OwnerDetailOutDto ownerDetailOutDto = new OwnerDetailOutDto();
//        ownerDetailOutDto.setId(owner.getId());
//        ownerDetailOutDto.setFirstName(owner.getFirstName());
//        ownerDetailOutDto.setLastName(owner.getLastName());
//        ownerDetailOutDto.setBirthDate(owner.getBirthDate());
//        ownerDetailOutDto.setCity(owner.getCity());
//        ownerDetailOutDto.setStreet(owner.getStreet());
//        ownerDetailOutDto.setZipCode(owner.getZipCode());
//        ownerDetailOutDto.setNumberOfHouse(owner.getNumberOfHouse());
//        ownerDetailOutDto.setCars(carPage);
//        return ownerDetailOutDto;
//    }
/*
    public static OwnerDetailOutDto convertToOwnerDetailOutDto(Owner owner) {
        OwnerDetailOutDto ownerDetailOutDto = new OwnerDetailOutDto();
        ownerDetailOutDto.setId(owner.getId());
        ownerDetailOutDto.setFirstName(owner.getFirstName());
        ownerDetailOutDto.setLastName(owner.getLastName());
        ownerDetailOutDto.setBirthDate(owner.getBirthDate());
        ownerDetailOutDto.setCity(owner.getCity());
        ownerDetailOutDto.setStreet(owner.getStreet());
        ownerDetailOutDto.setZipCode(owner.getZipCode());
        ownerDetailOutDto.setNumberOfHouse(owner.getNumberOfHouse());
        List<CarInOwnerDto> carInOwnerDtos = new ArrayList<>();
        for (CarOwner carOwner : owner.getCarOwners().stream().toList()) {
            CarInOwnerDto carInOwnerDto = new CarInOwnerDto();
            carInOwnerDto.setId(carOwner.getCar().getId());
            carInOwnerDto.setColor(carOwner.getCar().getColor());
            carInOwnerDto.setEmissionStandard(carOwner.getCar().getEmissionStandard());
            carInOwnerDto.setEnginePower(carOwner.getCar().getEnginePower());
            carInOwnerDto.setManufacturer(carOwner.getCar().getManufacturer());
            carInOwnerDto.setType(carOwner.getCar().getType());
            carInOwnerDto.setSPZ(carOwner.getCar().getSPZ());
            carInOwnerDto.setInDeposit(carOwner.getCar().isInDeposit());
            carInOwnerDto.setVin(carOwner.getCar().getVin());
            carInOwnerDto.setTorque(carOwner.getCar().getTorque());
            carInOwnerDto.setYearOfCreation(carOwner.getCar().getYearOfCreation());

            carInOwnerDto.setStartOfSignUp(carOwner.getStartOfSignUp());
            carInOwnerDto.setEndOfSignUp(carOwner.getEndOfSignUp());

            carInOwnerDtos.add(carInOwnerDto);

        }
        ownerDetailOutDto.setCars(carInOwnerDtos);
        return ownerDetailOutDto;
    }

 */
}
