package cz.upce.nnpro_stk_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Set<FaultInspection> faultInspection;

    public Set<FaultInspection> getFaultInspection() {
        return faultInspection;
    }

    public void setFaultInspection(Set<FaultInspection> faultInspection) {
        this.faultInspection = faultInspection;
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
