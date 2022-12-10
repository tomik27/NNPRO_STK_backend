package cz.upce.nnpro_stk_backend.dtos;

import cz.upce.nnpro_stk_backend.entities.Car;
import cz.upce.nnpro_stk_backend.entities.Inspection;
import cz.upce.nnpro_stk_backend.entities.User;

import java.util.List;

public class UsersInspectionsCarsDto {
    List<User> users;
    List<InspectionOutDto> inspections;
    List<Car> cars;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<InspectionOutDto> getInspections() {
        return inspections;
    }

    public void setInspections(List<InspectionOutDto> inspections) {
        this.inspections = inspections;
    }
}
