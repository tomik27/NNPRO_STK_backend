package cz.upce.nnpro_stk_backend.dtos;

import cz.upce.nnpro_stk_backend.entities.Fault;
import cz.upce.nnpro_stk_backend.entities.FaultOfInspection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class InspectionOutDto {
    private long Id;
   // @NotBlank(message = "inspectionTime is mandatory")
    private int inspectionTime;
    @NotNull(message = "Date of inspection is mandatory")
    private LocalDate date;
    @NotBlank(message = "Result of inspection is mandatory")
    private String result;
    private Long user;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public List<FaultOfInspectionDto> getFaultsOfInspectionList() {
        return faultsOfInspectionList;
    }

    public void setFaultsOfInspectionList(List<FaultOfInspectionDto> faultsOfInspectionList) {
        this.faultsOfInspectionList = faultsOfInspectionList;
    }
}
