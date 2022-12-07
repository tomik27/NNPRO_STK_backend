package cz.upce.nnpro_stk_backend.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class InspectionWithFaultsInDto {
   // @NotBlank(message = "inspectionTime is mandatory")
    private int inspectionTime;
    @NotNull(message = "Date of inspection is mandatory")
    private LocalDate date;
    private Long user;
    private Long branchOffice;
    private Long geometryFault;
    private Long lightningFault;
    private Long wheelFault;
    private Long bodyFault;
    private Long brakesFault;

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

    public Long getGeometryFault() {
        return geometryFault;
    }

    public void setGeometryFault(Long geometryFault) {
        this.geometryFault = geometryFault;
    }

    public Long getLightningFault() {
        return lightningFault;
    }

    public void setLightningFault(Long lightningFault) {
        this.lightningFault = lightningFault;
    }

    public Long getWheelFault() {
        return wheelFault;
    }

    public void setWheelFault(Long wheelFault) {
        this.wheelFault = wheelFault;
    }

    public Long getBodyFault() {
        return bodyFault;
    }

    public void setBodyFault(Long bodyFault) {
        this.bodyFault = bodyFault;
    }

    public Long getBrakesFault() {
        return brakesFault;
    }

    public void setBrakesFault(Long brakesFault) {
        this.brakesFault = brakesFault;
    }
}
