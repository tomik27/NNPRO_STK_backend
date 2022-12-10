package cz.upce.nnpro_stk_backend.dtos;

import cz.upce.nnpro_stk_backend.entities.Role;

public class UserWageDto {
    private Role role;
    private String username;
    private int hourRate;
    //  @NotBlank(message = "declarationOfTax is mandatory.")
    private boolean declarationOfTax;
    //   @NotBlank(message = "Number of children is mandatory.")
    private int numberOfChildren;

    private int monthSalary;

    private int numberOfInspection;

    private int numberOfHoursWorked;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumberOfHoursWorked() {
        return numberOfHoursWorked;
    }

    public void setNumberOfHoursWorked(int numberOfHoursWorked) {
        this.numberOfHoursWorked = numberOfHoursWorked;
    }

    public int getNumberOfInspection() {
        return numberOfInspection;
    }

    public void setNumberOfInspection(int numberOfInspection) {
        this.numberOfInspection = numberOfInspection;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getHourRate() {
        return hourRate;
    }

    public void setHourRate(int hourRate) {
        this.hourRate = hourRate;
    }

    public boolean isDeclarationOfTax() {
        return declarationOfTax;
    }

    public void setDeclarationOfTax(boolean declarationOfTax) {
        this.declarationOfTax = declarationOfTax;
    }



    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public int getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(int monthSalary) {
        this.monthSalary = monthSalary;
    }
}
