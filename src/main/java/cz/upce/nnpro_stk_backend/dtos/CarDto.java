package cz.upce.nnpro_stk_backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.upce.nnpro_stk_backend.entities.Inspection;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CarDto {
    private String operable;
    @NotBlank(message = "SPZ is mandatory.")
    private String spz;
    private String vin;
    private LocalDate expiryDateOfSTK;

    public String getOperable() {
        return operable;
    }

    public void setOperable(String operable) {
        this.operable = operable;
    }

    public String getSpz() {
        return spz;
    }

    public LocalDate getExpiryDateOfSTK() {
        return expiryDateOfSTK;
    }

    public void setExpiryDateOfSTK(LocalDate expiryDateOfSTK) {
        this.expiryDateOfSTK = expiryDateOfSTK;
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

}
