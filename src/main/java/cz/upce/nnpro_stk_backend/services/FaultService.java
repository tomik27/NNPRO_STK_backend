package cz.upce.nnpro_stk_backend.services;

import cz.upce.nnpro_stk_backend.dtos.BranchOfficeInDto;
import cz.upce.nnpro_stk_backend.dtos.FaultDto;
import cz.upce.nnpro_stk_backend.dtos.UserDto;
import cz.upce.nnpro_stk_backend.entities.*;
import cz.upce.nnpro_stk_backend.repositories.FaultRepository;
import cz.upce.nnpro_stk_backend.repositories.TypeOfFaultRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

    public Fault editFault(Long faultId, Fault fault) {
        if (faultRepository.existsByDescription(fault.getDescription())) {
            throw new IllegalArgumentException("The description already exists.");
        }
       /* Optional<Role> role = roleRepository.findById(userDto.getRole() == null ? 0 : userDto.getRole());
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found!"));
        User editedUser = ConversionService.convertToUser(userDto, user, role);
        User save = userRepository.save(editedUser);*/
        return new Fault();
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
