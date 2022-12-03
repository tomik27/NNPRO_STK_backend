package cz.upce.nnpro_stk_backend.controllers;

import cz.upce.nnpro_stk_backend.dtos.FaultOfInspectionDto;
import cz.upce.nnpro_stk_backend.dtos.InspectionInDto;
import cz.upce.nnpro_stk_backend.entities.Fault;
import cz.upce.nnpro_stk_backend.entities.Inspection;
import cz.upce.nnpro_stk_backend.services.InspectionService;
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
@RequestMapping("/inspection")
@CrossOrigin
@SecurityRequirement(name = "NNPRO_API")
@Tag(name = "Inspection controller")
public class InspectionController {
    private final InspectionService inspectionService;

    public InspectionController(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

    @Operation(summary = "Get Inspection info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspection returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Inspection not found",
                    content = @Content),})
    @GetMapping("/getInspection/{inspectionId}")
    public ResponseEntity<?> getInspection(@PathVariable Long inspectionId) {
        return ResponseEntity.ok(inspectionService.getInspection(inspectionId));
    }

    @Operation(summary = "Get all inspections ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspection returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @GetMapping("/getAllInspections")
    public ResponseEntity<?> getAllInspections() {
        return ResponseEntity.ok(inspectionService.getAllInspections());
    }

    @Operation(summary = "Add inspection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspection added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @PostMapping("/addInspection")
    public ResponseEntity<?> addInspection(@RequestBody @Valid InspectionInDto inspectionInDto) {
        return ResponseEntity.ok(inspectionService.addInspection(inspectionInDto));
    }

    @Operation(summary = "Remove inspection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspection removed and returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fault.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Inspection not found",
                    content = @Content),})
    @PreAuthorize("hasRole('ROLE_Admin') || hasRole('ROLE_Okres')")
    @DeleteMapping("/removeInspection/{inspectionId}")
    public ResponseEntity<?> removeFault(@PathVariable Long inspectionId) {
        return ResponseEntity.ok(inspectionService.removeInspection(inspectionId));
    }

    @Operation(summary = "Add fault to inspection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspection fault added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @PostMapping("/addFaultToInspection")
    public ResponseEntity<?> addFaultToInspection(@RequestBody @Valid FaultOfInspectionDto faultOfInspectionDto) {
        return ResponseEntity.ok(inspectionService.addFaultToInspection(faultOfInspectionDto));
    }


    /*@Operation(summary = "Get all faults in inspections ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Faults returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content)})
    @GetMapping("/getAllInspections")
    public ResponseEntity<?> getAllInspections() {
        return ResponseEntity.ok(inspectionService.getAllInspections());
    }*/
}
