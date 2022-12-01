package cz.upce.nnpro_stk_backend.services;

import cz.upce.nnpro_stk_backend.dtos.BranchOfficeInDto;
import cz.upce.nnpro_stk_backend.dtos.FaultDto;
import cz.upce.nnpro_stk_backend.entities.*;
import cz.upce.nnpro_stk_backend.repositories.FaultRepository;
import cz.upce.nnpro_stk_backend.repositories.TypeOfFaultRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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

        Fault fault= new Fault();
        fault.setDescription(faultDto.getDescription());
        fault.setTypeOfFault(faultDto.getTypeOfFault());
        Fault save = faultRepository.save(fault);
        return save;
    }


    @PostConstruct
    public void init() {
        if (typeOfFaultRepository.count() != 3) {
            typeOfFaultRepository.deleteAll();
            typeOfFaultRepository.save(new TypeOfFault( "Lehká závada","A"));
            typeOfFaultRepository.save(new TypeOfFault( "Těžká závada","B"));
            typeOfFaultRepository.save(new TypeOfFault( "Nebezpečná závada","C"));
        }
    }
}
