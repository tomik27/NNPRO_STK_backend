package cz.upce.nnpro_stk_backend.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDto {
    @NotBlank(message = "Username is mandatory.")
    private String username;
    private String email;
    @NotBlank(message = "Password is mandatory.")
    private String password;
    private String jobPosition;
    @NotNull(message = "Role id is mandatory.")
    private Long role;
    private int hourRate;
    private boolean declarationOfTax;
    private int numberOfChildren;
    private String fullName;

    public boolean isDeclarationOfTax() {
        return declarationOfTax;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getHourRate() {
        return hourRate;
    }

    public void setHourRate(int hourRate) {
        this.hourRate = hourRate;
    }

    public boolean getDeclarationOfTax() {
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

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }
}
