package cz.upce.nnpro_stk_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String operable;
    @NotBlank(message = "SPZ is mandatory.")
    private String spz;
    private String vin;
    private LocalDate expiryDateOfSTK;

    /*@ManyToOne
    @JoinColumn(name = "inspection_id")
    private Inspection inspection;*/
    @JsonIgnore
    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE)
    private Set<Inspection> inspections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperable() {
        return operable;
    }

    public void setOperable(String operable) {
        this.operable = operable;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Set<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(Set<Inspection> inspections) {
        this.inspections = inspections;
    }

    public LocalDate getExpiryDateOfSTK() {
        return expiryDateOfSTK;
    }

    public void setExpiryDateOfSTK(LocalDate expiryDateOfSTK) {
        this.expiryDateOfSTK = expiryDateOfSTK;
    }
}
