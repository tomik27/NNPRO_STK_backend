package cz.upce.nnpro_stk_backend.dtos;

import cz.upce.nnpro_stk_backend.entities.BranchOffice;
import cz.upce.nnpro_stk_backend.entities.Car;
import cz.upce.nnpro_stk_backend.entities.Fault;
import cz.upce.nnpro_stk_backend.entities.FaultOfInspection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class InspectionOutDto {
   // @NotBlank(message = "inspectionTime is mandatory")
    private int inspectionTime;
    @NotNull(message = "Date of inspection is mandatory")
    private LocalDate date;
    @NotBlank(message = "Result of inspection is mandatory")
    private UserDto userDto;
    private BranchOfficeDto branchDto;

    private CarDto carDto;

    private List<FaultOfInspectionDto> faultsOfInspectionList;


    public int getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(int inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public BranchOfficeDto getBranchDto() {
        return branchDto;
    }

    public void setBranchDto(BranchOfficeDto branchDto) {
        this.branchDto = branchDto;
    }

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    public List<FaultOfInspectionDto> getFaultsOfInspectionList() {
        return faultsOfInspectionList;
    }

    public void setFaultsOfInspectionList(List<FaultOfInspectionDto> faultsOfInspectionList) {
        this.faultsOfInspectionList = faultsOfInspectionList;
    }
}
