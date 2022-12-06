package cz.upce.nnpro_stk_backend.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Fault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToOne
    private TypeOfFault typeOfFault;

    @OneToMany(mappedBy = "fault", cascade = CascadeType.REMOVE)
    private Set<FaultOfInspection> faultOfInspection;

    public Set<FaultOfInspection> getFaultInspection() {
        return faultOfInspection;
    }

    public void setFaultInspection(Set<FaultOfInspection> faultOfInspection) {
        this.faultOfInspection = faultOfInspection;
    }

    public Fault(String description, TypeOfFault typeOfFault) {
        this.description = description;
        this.typeOfFault = typeOfFault;
    }

    public Fault() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
