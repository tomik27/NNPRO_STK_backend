package cz.upce.nnpro_stk_backend.dtos;

import cz.upce.nnpro_stk_backend.entities.Inspection;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CarOutDto {

    private Long id;
    private int inspectionTime;
    @NotNull(message = "Date of inspection is mandatory")
    private LocalDate date;

    private InspectionDto inspectionDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public InspectionDto getInspectionDto() {
        return inspectionDto;
    }

    public void setInspectionDto(InspectionDto inspectionDto) {
        this.inspectionDto = inspectionDto;
    }
}
