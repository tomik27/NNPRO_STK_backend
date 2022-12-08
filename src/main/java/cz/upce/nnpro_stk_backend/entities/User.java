package cz.upce.nnpro_stk_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is mandatory.")
    private String username;
    private String email;
    @NotBlank(message = "Password is mandatory.")
    private String password;
    private String jobPosition;
    @ManyToOne
    @JoinColumn(name = "branchOffice_id")
    @JsonIgnore
    private BranchOffice branchOffice;
    @OneToOne
    private Role role;
    private int hourRate;
  //  @NotBlank(message = "declarationOfTax is mandatory.")
    private boolean declarationOfTax;
 //   @NotBlank(message = "Number of children is mandatory.")
    private int numberOfChildren;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Inspection> inspections;



    public Set<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(Set<Inspection> inspections) {
        this.inspections = inspections;
    }

    public BranchOffice getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(BranchOffice branchOffice) {
        this.branchOffice = branchOffice;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
