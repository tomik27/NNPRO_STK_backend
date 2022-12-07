package cz.upce.nnpro_stk_backend.dtos;

public class FaultOfInspectionInDto {
    private Long inspection;
    private Long fault;

    public Long getInspection() {
        return inspection;
    }

    public void setInspection(Long inspection) {
        this.inspection = inspection;
    }

    public Long getFault() {
        return fault;
    }

    public void setFault(Long fault) {
        this.fault = fault;
    }
}
