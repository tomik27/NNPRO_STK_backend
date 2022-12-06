package cz.upce.nnpro_stk_backend.services;

import cz.upce.nnpro_stk_backend.dtos.*;
import cz.upce.nnpro_stk_backend.entities.*;
import cz.upce.nnpro_stk_backend.repositories.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class InspectionService {
    private final InspectionRepository inspectionRepository;
    private final UserRepository userRepository;
    private final FaultInspectionRepository faultInspectionRepository;
    private final FaultRepository faultRepository;
    private final PdfService pdfService;
    private final BranchOfficeRepository branchOfficeRepository;

    public InspectionService(InspectionRepository inspectionRepository, UserRepository userRepository, FaultInspectionRepository faultInspectionRepository, FaultRepository faultRepository, PdfService pdfService, BranchOfficeRepository branchOfficeRepository) {
        this.inspectionRepository = inspectionRepository;
        this.userRepository = userRepository;
        this.faultInspectionRepository = faultInspectionRepository;
        this.faultRepository = faultRepository;
        this.pdfService = pdfService;
        this.branchOfficeRepository = branchOfficeRepository;
    }

    public Inspection addInspection(InspectionInDto inspectionInDto) {
        User user = userRepository.findById(inspectionInDto.getUser()).orElseThrow(() -> new NoSuchElementException("User not found!"));
        BranchOffice branchOffice = branchOfficeRepository.findById(inspectionInDto.getBranchOffice()).orElseThrow(() -> new NoSuchElementException("Brnach not found!"));
        Inspection inspection = ConversionService.convertToInspection(inspectionInDto, user,branchOffice);

        Inspection save = inspectionRepository.save(inspection);
        return save;
    }

    public Inspection removeInspection(Long inspectionId) {
        Inspection inspection = inspectionRepository.findById(inspectionId).orElseThrow(() -> new NoSuchElementException("Inspection not found!"));
        inspectionRepository.deleteById(inspectionId);
        return inspection;
    }
    public InspectionOutDto  getInspection(Long inspectionId) {
        Inspection inspection = inspectionRepository.findById(inspectionId).orElseThrow(() -> new NoSuchElementException("Inspection not found!"));
        Set<FaultOfInspection> faultsbyInspection_id = faultInspectionRepository.findByInspection_Id(inspection.getId());
        List<FaultOfInspectionDto> faultOfInspectionDtos = ConversionService.convertToListOfFaultDto(faultsbyInspection_id);
        InspectionOutDto inspectionOutDto = ConversionService.convertToInspectionOutDto(inspection, faultOfInspectionDtos);
        return inspectionOutDto;
    }
    public List<Inspection> getAllInspections() {
        List<Inspection> inspections = inspectionRepository.findAll();
        return inspections;
    }

    public FaultOfInspection addFaultToInspection(InspectionFaultsOutDto faultOfInspectionDto) {
        Inspection inspection = inspectionRepository.findById(faultOfInspectionDto.getInspection()).orElseThrow(() -> new NoSuchElementException("Inspection not found!"));
        Fault fault = faultRepository.findById(faultOfInspectionDto.getFault()).orElseThrow(() -> new NoSuchElementException("Fault not found!"));

        FaultOfInspection faultOfInspection =new FaultOfInspection();
        faultOfInspection.setInspection(inspection);
        faultOfInspection.setFault(fault);
        FaultOfInspection save = faultInspectionRepository.save(faultOfInspection);
        return save;
    }

    public Inspection editInspection(Long inspectionId, InspectionInDto inspectionInDto) {
        inspectionRepository.findById(inspectionId).orElseThrow(() -> new NoSuchElementException("Inspection not found!"));
        User user = userRepository.findById(inspectionInDto.getUser()).orElseThrow(() -> new NoSuchElementException("User not found!"));
        BranchOffice branchOffice = branchOfficeRepository.findById(inspectionInDto.getBranchOffice()).orElseThrow(() -> new NoSuchElementException("Branch not found!"));
        Inspection inspection = ConversionService.convertToInspection(inspectionInDto, user, branchOffice);
        inspection.setId(inspectionId);
        Inspection save = inspectionRepository.save(inspection);
        return save;
    }

    public ByteArrayInputStream getPDF(Long inspectionId) {
      return  pdfService.createPdf();
    }

    public FaultOfInspection removeFaultFromInspection(Long inspectionId, Long faultId) {
        FaultOfInspection faultOfInspection = faultInspectionRepository.existsByCarAndEndOfSignUpIsNull(inspectionId, faultId);
        //todo dodělat pokud neexistuje error, pokud existuje smazat
   return faultOfInspection;
    }
}
