package cz.upce.nnpro_stk_backend.controllers;

import cz.upce.nnpro_stk_backend.dtos.BranchOfficeInDto;
import cz.upce.nnpro_stk_backend.dtos.CarDto;
import cz.upce.nnpro_stk_backend.dtos.InspectionInDto;
import cz.upce.nnpro_stk_backend.entities.BranchOffice;
import cz.upce.nnpro_stk_backend.entities.Fault;
import cz.upce.nnpro_stk_backend.entities.Inspection;
import cz.upce.nnpro_stk_backend.services.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/car")
@CrossOrigin
@SecurityRequirement(name = "NNPRO_API")
@Tag(name = "Car controller")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Get Car info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspection returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car not found",
                    content = @Content),})
    @GetMapping("/getCar/{carId}")
    public ResponseEntity<?> getCar(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.getCar(carId));
    }

    @Operation(summary = "Get all cars ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @GetMapping("/getAllCars")
    public ResponseEntity<?> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @Operation(summary = "Add car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @PostMapping("/addCar")
    public ResponseEntity<?> addCar(@RequestBody @Valid CarDto carDto) {
        return ResponseEntity.ok(carService.addCar(carDto));
    }

    @Operation(summary = "Remove car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car removed and returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fault.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car not found",
                    content = @Content),})
    @PreAuthorize("hasRole('ROLE_Admin') || hasRole('ROLE_Technik')")
    @DeleteMapping("/removeCar/{carId}")
    public ResponseEntity<?> removeCar(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.removeCar(carId));
    }

    @Operation(summary = "Edit car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car edited and returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BranchOffice.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car not found",
                    content = @Content),})
    @PreAuthorize("hasRole('ROLE_Admin') || hasRole('ROLE_Technik')")
    @PutMapping("/editCar/{carId}")
    public ResponseEntity<?> editCar(@PathVariable Long carId, @RequestBody @Valid CarDto carDto) {
        return ResponseEntity.ok(carService.editCar(carId, carDto));
    }

}
