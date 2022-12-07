package cz.upce.nnpro_stk_backend.controllers;

import cz.upce.nnpro_stk_backend.dtos.CarDto;
import cz.upce.nnpro_stk_backend.dtos.FaultOfInspectionInDto;
import cz.upce.nnpro_stk_backend.dtos.InspectionFaultsOutDto;
import cz.upce.nnpro_stk_backend.dtos.InspectionInDto;
import cz.upce.nnpro_stk_backend.entities.BranchOffice;
import cz.upce.nnpro_stk_backend.entities.Fault;
import cz.upce.nnpro_stk_backend.entities.FaultOfInspection;
import cz.upce.nnpro_stk_backend.entities.Inspection;
import cz.upce.nnpro_stk_backend.services.InspectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

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

    @Operation(summary = "Edit inspection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car edited and returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Inspection not found",
                    content = @Content),})
    @PreAuthorize("hasRole('ROLE_Admin') || hasRole('ROLE_Technik')")
    @PutMapping("/editInspection/{inspectionId}")
    public ResponseEntity<?> editCar(@PathVariable Long inspectionId, @RequestBody @Valid InspectionInDto inspectionInDto) {
        return ResponseEntity.ok(inspectionService.editInspection(inspectionId, inspectionInDto));
    }

    @Operation(summary = "Remove inspection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspection removed and returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Inspection not found",
                    content = @Content),})
    @PreAuthorize("hasRole('ROLE_Admin') || hasRole('ROLE_Technik')")
    @DeleteMapping("/removeInspection/{inspectionId}")
    public ResponseEntity<?> removeCar(@PathVariable Long inspectionId) {
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
    public ResponseEntity<?> addFaultToInspection(@RequestBody @Valid InspectionFaultsOutDto faultOfInspectionDto) {
        return ResponseEntity.ok(inspectionService.addFaultToInspection(faultOfInspectionDto));
    }

    @Operation(summary = "Remove fault from inspection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspection fault removed",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FaultOfInspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
    @ApiResponse(responseCode = "500", description = "Inspection or fault not found",
            content = @Content),})
    @DeleteMapping("/removeFaultFromInspection")
    public ResponseEntity<?> removeFaultFromInspection(@RequestBody @Valid FaultOfInspectionInDto faultOfInspectionInDto) {
        return ResponseEntity.ok(inspectionService.removeFaultFromInspection(faultOfInspectionInDto));
    }


    @Operation(summary = "Get PDF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PDF of inspection returned",
                    content = {@Content(mediaType = "application/pdf",
                            schema = @Schema(implementation = Inspection.class))}),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Inspection not found",
                    content = @Content),})
    @GetMapping(value="/getPDF/{inspectionId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPDF(@PathVariable Long inspectionId) {
        ByteArrayInputStream pdf = inspectionService.getPDF(inspectionId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment; filename=report_id"+inspectionId+".pdf");
        httpHeaders.add("Content-Type", "application/pdf");


        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
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
