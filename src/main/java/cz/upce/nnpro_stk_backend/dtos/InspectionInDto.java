package cz.upce.nnpro_stk_backend.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class InspectionInDto {
   // @NotBlank(message = "inspectionTime is mandatory")
    private int inspectionTime;
    @NotNull(message = "Date of inspection is mandatory")
    private LocalDate date;
    private Long user;
    private Long branchOffice;

    public Long getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(Long branchOffice) {
        this.branchOffice = branchOffice;
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


    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
