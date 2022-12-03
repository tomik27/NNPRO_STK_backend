package cz.upce.nnpro_stk_backend.services;

import cz.upce.nnpro_stk_backend.dtos.FaultOfInspectionDto;
import cz.upce.nnpro_stk_backend.dtos.InspectionInDto;
import cz.upce.nnpro_stk_backend.entities.*;
import cz.upce.nnpro_stk_backend.repositories.FaultInspectionRepository;
import cz.upce.nnpro_stk_backend.repositories.FaultRepository;
import cz.upce.nnpro_stk_backend.repositories.InspectionRepository;
import cz.upce.nnpro_stk_backend.repositories.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InspectionService {
    private final InspectionRepository inspectionRepository;
    private final UserRepository userRepository;
    private final FaultInspectionRepository faultInspectionRepository;
    private final FaultRepository faultRepository;

    public InspectionService(InspectionRepository inspectionRepository, UserRepository userRepository, FaultInspectionRepository faultInspectionRepository, FaultRepository faultRepository) {
        this.inspectionRepository = inspectionRepository;
        this.userRepository = userRepository;
        this.faultInspectionRepository = faultInspectionRepository;
        this.faultRepository = faultRepository;
    }

    public Inspection addInspection(InspectionInDto inspectionInDto) {
        User user = userRepository.findById(inspectionInDto.getUser()).orElseThrow(() -> new NoSuchElementException("User not found!"));
        Inspection inspection = ConversionService.convertToInspection(inspectionInDto, user);
        Inspection save = inspectionRepository.save(inspection);
        return save;
    }

    public Inspection removeInspection(Long inspectionId) {
        Inspection inspection = inspectionRepository.findById(inspectionId).orElseThrow(() -> new NoSuchElementException("Inspection not found!"));
        inspectionRepository.deleteById(inspectionId);
        return inspection;
    }
    public Inspection getInspection(Long inspectionId) {
        Inspection inspection = inspectionRepository.findById(inspectionId).orElseThrow(() -> new NoSuchElementException("Inspection not found!"));
        return inspection;
    }
    public List<Inspection> getAllInspections() {
        List<Inspection> inspections = inspectionRepository.findAll();
        return inspections;
    }

    public FaultOfInspection addFaultToInspection(FaultOfInspectionDto faultOfInspectionDto) {
        Inspection inspection = inspectionRepository.findById(faultOfInspectionDto.getInspection()).orElseThrow(() -> new NoSuchElementException("Inspection not found!"));
        Fault fault = faultRepository.findById(faultOfInspectionDto.getFault()).orElseThrow(() -> new NoSuchElementException("Fault not found!"));

        FaultOfInspection faultOfInspection =new FaultOfInspection();
        faultOfInspection.setInspection(inspection);
        faultOfInspection.setFault(fault);
        FaultOfInspection save = faultInspectionRepository.save(faultOfInspection);
        return save;
    }
}
