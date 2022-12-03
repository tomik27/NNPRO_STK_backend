package cz.upce.nnpro_stk_backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.upce.nnpro_stk_backend.entities.Inspection;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

public class CarDto {
    private String operable;
    @NotBlank(message = "SPZ is mandatory.")
    private String spz;
    private String vin;
    private Long inspectionId;

    public String getOperable() {
        return operable;
    }

    public void setOperable(String operable) {
        this.operable = operable;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Long getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspectionId = inspectionId;
    }
}
