package cz.upce.nnpro_stk_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;
@Entity
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //@NotBlank(message = "inspectionTime is mandatory")
    private int inspectionTime;
    @OneToMany(mappedBy = "inspection", cascade = CascadeType.REMOVE)
    private Set<FaultOfInspection> faultOfInspections;
    @OneToMany(mappedBy = "inspection", cascade = CascadeType.REMOVE)
    private Set<Car> cars;
    @NotNull(message = "Date of inspection is mandatory")
    private LocalDate date;
   // @NotBlank(message = "Result of inspection is mandatory")
    private String result;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(int inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

   public Set<FaultOfInspection> getFaultInspections() {
        return faultOfInspections;
    }

    public void setFaultInspections(Set<FaultOfInspection> faultOfInspections) {
        this.faultOfInspections = faultOfInspections;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
