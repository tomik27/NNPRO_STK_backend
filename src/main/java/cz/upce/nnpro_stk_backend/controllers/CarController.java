package cz.upce.nnpro_stk_backend.controllers;

import cz.upce.nnpro_stk_backend.dtos.*;
import cz.upce.nnpro_stk_backend.entities.BranchOffice;
import cz.upce.nnpro_stk_backend.entities.Car;
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
            @ApiResponse(responseCode = "200", description = "Car returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarOutDto.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car not found",
                    content = @Content),})
    @GetMapping("/getCar/{carId}")
    public ResponseEntity<?> getCar(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.getCar(carId));
    }

    @Operation(summary = "Get Car info by spz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarOutDto.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car not found",
                    content = @Content),})
    @GetMapping("/getCarBySpz/{spz}")
    public ResponseEntity<?> getCarBySpz(@PathVariable String spz) {
        return ResponseEntity.ok(carService.getCarBySpz(spz));
    }



    @Operation(summary = "Get all cars ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class))}),
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
                            schema = @Schema(implementation = Car.class))}),
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
                            schema = @Schema(implementation = Car.class))}),
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
                            schema = @Schema(implementation = Car.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Car not found",
                    content = @Content),})
    @PreAuthorize("hasRole('ROLE_Admin') || hasRole('ROLE_Technik')")
    @PutMapping("/editCar/{carId}")
    public ResponseEntity<?> editCar(@PathVariable Long carId, @RequestBody @Valid CarDto carDto) {
        return ResponseEntity.ok(carService.editCar(carId, carDto));
    }

    @Operation(summary = "Check if car is stolen by vin", description = "It has to have another app started (European register of stolen vehicles)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Check went through. ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = boolean.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @GetMapping("/isCarStolenByVin/{vin}")
    public ResponseEntity<?> isCarStolenByVin(@PathVariable String vin) {
        return ResponseEntity.ok(carService.isCarStolenByVin(vin));
    }

    @Operation(summary = "Check if car is exist by spz", description = "return boolean")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Check went through. ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = boolean.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @GetMapping("/isCarExistBySpz/{spz}")
    public ResponseEntity<?> isCarExistBySpz(@PathVariable String spz) {
        return ResponseEntity.ok(carService.isCarExistBySpz(spz));
    }

    @Operation(summary = "Check if car is stolen by spz", description = "It has to have another app started (European register of stolen vehicles)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Check went through. ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = boolean.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @GetMapping("/isCarStolenBySpz/{spz}")
    public ResponseEntity<?> isCarStolenBySpz(@PathVariable String spz) {
        return ResponseEntity.ok(carService.isCarStolenBySpz(spz));
    }

    @Operation(summary = "Check if car is stolen by spz", description = "It has to have another app started (European register of stolen vehicles)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Check went through. ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarFromCrvDto.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @GetMapping("/getCarInfoFromCrvBySpz/{spz}")
    public ResponseEntity<?> getCarInfoFromCrvBySpz(@PathVariable String spz) {
        return ResponseEntity.ok(carService.getCarInfoFromCrvBySpz(spz));
    }

}
