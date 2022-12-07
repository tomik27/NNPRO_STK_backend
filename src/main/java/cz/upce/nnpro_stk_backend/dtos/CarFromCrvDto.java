package cz.upce.nnpro_stk_backend.dtos;

import java.time.LocalDate;
import java.util.List;

public class CarFromCrvDto {
    private Long id;
    private String manufacturer;
    private String type;
    private String SPZ;
    private String color;
    private double enginePower;
    private double emissionStandard;
    private double torque;
    private boolean isInDeposit = false;
    private LocalDate yearOfCreation;
    private String vin;
    //private List<OwnerInCarDto> owners;
}
