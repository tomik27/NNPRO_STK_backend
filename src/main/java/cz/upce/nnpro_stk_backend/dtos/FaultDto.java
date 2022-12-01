package cz.upce.nnpro_stk_backend.dtos;

import cz.upce.nnpro_stk_backend.entities.TypeOfFault;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

public class FaultDto {
    private String description;
    @NotBlank(message = "TypeOFault is mandatory.")
    private TypeOfFault typeOfFault;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeOfFault getTypeOfFault() {
        return typeOfFault;
    }

    public void setTypeOfFault(TypeOfFault typeOfFault) {
        this.typeOfFault = typeOfFault;
    }
}
