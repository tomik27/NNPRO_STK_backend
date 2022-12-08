package cz.upce.nnpro_stk_backend.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class InspectionDto {
    private Long id;
    private int inspectionTime;
    @NotNull(message = "Date of inspection is mandatory")
    private LocalDate date;
}
