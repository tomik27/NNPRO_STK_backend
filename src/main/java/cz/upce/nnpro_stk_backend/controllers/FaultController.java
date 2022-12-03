package cz.upce.nnpro_stk_backend.controllers;

import cz.upce.nnpro_stk_backend.dtos.FaultDto;
import cz.upce.nnpro_stk_backend.entities.Fault;
import cz.upce.nnpro_stk_backend.entities.Role;
import cz.upce.nnpro_stk_backend.repositories.TypeOfFaultRepository;
import cz.upce.nnpro_stk_backend.services.FaultService;
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
@RequestMapping("/fault")
@CrossOrigin
@SecurityRequirement(name = "NNPRO_API")
@Tag(name = "Fault controller")
public class FaultController {
    private final FaultService faultService;
    private final TypeOfFaultRepository typeOfFaultRepository;

    public FaultController(FaultService faultService, TypeOfFaultRepository typeOfFaultRepository) {
        this.faultService = faultService;
        this.typeOfFaultRepository = typeOfFaultRepository;
    }
    @Operation(summary = "Get Fault info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fault returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fault.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Fault not found",
                    content = @Content),})
    @GetMapping("/getFault/{faultId}")
    public ResponseEntity<?> getOffice(@PathVariable Long faultId) {
        return ResponseEntity.ok(faultService.getFault(faultId));
    }

    @Operation(summary = "Get all faults ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Faults returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fault.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @GetMapping("/getAllFaults")
    public ResponseEntity<?> getAllFaults() {
        return ResponseEntity.ok(faultService.getAllFaults());
    }

    @Operation(summary = "Add fault")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fault added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fault.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @PostMapping("/addFault")
    public ResponseEntity<?> addOffice(@RequestBody @Valid FaultDto faultDto) {
        return ResponseEntity.ok(faultService.addFault(faultDto));
    }

    @Operation(summary = "Remove fault")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fault removed and returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fault.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Fault not found",
                    content = @Content),})
    @PreAuthorize("hasRole('ROLE_Admin') || hasRole('ROLE_Okres')")
    @DeleteMapping("/removeFault/{faultId}")
    public ResponseEntity<?> removeFault(@PathVariable Long faultId) {
        return ResponseEntity.ok(faultService.removeFault(faultId));
    }

    @Operation(summary = "Get all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles returned (List<Role>)",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Role.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "User not found",
                    content = @Content),})
    @SecurityRequirement(name = "NNPRO_API")
    @GetMapping("/getAllTypeOfFaults")
    public ResponseEntity<?> getAllTypeOfFaults() {
        return ResponseEntity.ok(typeOfFaultRepository.findAll());
    }

}
