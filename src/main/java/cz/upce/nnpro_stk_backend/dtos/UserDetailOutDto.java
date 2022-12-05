package cz.upce.nnpro_stk_backend.dtos;

import cz.upce.nnpro_stk_backend.entities.Role;

public class UserDetailOutDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String jobPosition;
    private Role role;
    private BranchOfficeDto branchOfficeDto;
    private int hourRate;
    //  @NotBlank(message = "declarationOfTax is mandatory.")
    private boolean declarationOfTax;
    //   @NotBlank(message = "Number of children is mandatory.")
    private int numberOfChildren;

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

    public BranchOfficeDto getBranchOfficeDto() {
        return branchOfficeDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBranchOfficeDto(BranchOfficeDto branchOfficeDto) {
        this.branchOfficeDto = branchOfficeDto;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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


    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
