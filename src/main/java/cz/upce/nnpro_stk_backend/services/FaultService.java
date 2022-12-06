package cz.upce.nnpro_stk_backend.services;

import cz.upce.nnpro_stk_backend.dtos.FaultDto;
import cz.upce.nnpro_stk_backend.entities.*;
import cz.upce.nnpro_stk_backend.repositories.FaultRepository;
import cz.upce.nnpro_stk_backend.repositories.TypeOfFaultRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FaultService {

    private final FaultRepository faultRepository;
    private final TypeOfFaultRepository typeOfFaultRepository;

    public FaultService(FaultRepository faultRepository, TypeOfFaultRepository typeOfFaultRepository) {
        this.faultRepository = faultRepository;
        this.typeOfFaultRepository = typeOfFaultRepository;
    }

    public Fault addFault(FaultDto faultDto) {
        if (faultRepository.existsByDescription(faultDto.getDescription())) {
            throw new IllegalArgumentException("The description already exists.");
        }
        //todo Jak se bude vybirat zavady? z comboboxu? Nebo se budou zadávat vždy novy popis?
       // if(typeOfFaultRepository.findb)

        Optional<TypeOfFault> typeOfFault = typeOfFaultRepository.findById(faultDto.getTypeOfFault() == null ? 0 : faultDto.getTypeOfFault());
        Fault fault = ConversionService.convertToFault(faultDto, typeOfFault);
       // fault.setDescription(faultDto.getDescription());
        //fault.setTypeOfFault(faultDto.getTypeOfFault());
        Fault save = faultRepository.save(fault);
        return save;
    }
    public Fault removeFault(Long faultId) {
        Fault fault = faultRepository.findById(faultId).orElseThrow(() -> new NoSuchElementException("Fault not found!"));
        faultRepository.deleteById(faultId);
        return fault;
    }

    public Fault getFault(Long faultID){
        Fault fault = faultRepository.findById(faultID).orElseThrow(() -> new NoSuchElementException("Fault not found!"));
             return fault;
    }

    public List<Fault> getAllFaults() {
        List<Fault> faults = faultRepository.findAll();
        return faults;
    }

    public Fault editFault(Long faultId,  FaultDto faultDto) {
         faultRepository.findById(faultId).orElseThrow(() -> new NoSuchElementException("Fault not found!"));

        if (faultRepository.existsByDescription(faultDto.getDescription())) {
            throw new IllegalArgumentException("The description already exists.");
        }
        TypeOfFault typeOfFault = typeOfFaultRepository.findById(faultDto.getTypeOfFault()).orElseThrow(() -> new NoSuchElementException("TypeOfFault not found!"));;
        Fault fault = ConversionService.convertToFault(faultDto, typeOfFault);
        fault.setId(faultId);
        Fault save = faultRepository.save(fault);
        return save;
    }

    @PostConstruct
    public void init() {
        TypeOfFault lehka   =  new TypeOfFault( "Lehká závada","A");
        TypeOfFault tezka   =  new TypeOfFault( "Těžká závada","B");
        TypeOfFault nebezpecna  = new TypeOfFault( "Nebezpečná závada","C");


        if (typeOfFaultRepository.count() != 3) {
            typeOfFaultRepository.deleteAll();
            typeOfFaultRepository.save(lehka);
            typeOfFaultRepository.save(tezka);
            typeOfFaultRepository.save(nebezpecna);
        }
        TypeOfFault A = typeOfFaultRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("TypeOfFault not found!"));
        TypeOfFault B = typeOfFaultRepository.findById(2L).orElseThrow(() -> new NoSuchElementException("TypeOfFault not found!"));
        TypeOfFault C = typeOfFaultRepository.findById(3L).orElseThrow(() -> new NoSuchElementException("TypeOfFault not found!"));

        if (faultRepository.count() != 15) {
            faultRepository.deleteAll();
            faultRepository.save(new Fault("brzdy",A));
            faultRepository.save(new Fault("geometrie",A));
            faultRepository.save(new Fault("osvětlení",A));
            faultRepository.save(new Fault("kola",A));
            faultRepository.save(new Fault("karoserie",A));

            faultRepository.save(new Fault("brzdy",B));
            faultRepository.save(new Fault("geometrie",B));
            faultRepository.save(new Fault("osvětlení",B));
            faultRepository.save(new Fault("kola",B));
            faultRepository.save(new Fault("karoserie",B));

            faultRepository.save(new Fault("brzdy",C));
            faultRepository.save(new Fault("geometrie",C));
            faultRepository.save(new Fault("osvětlení",C));
            faultRepository.save(new Fault("kola",C));
            faultRepository.save(new Fault("karoserie",C));


        }
    }
}
