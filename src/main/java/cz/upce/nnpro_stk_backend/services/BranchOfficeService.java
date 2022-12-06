package cz.upce.nnpro_stk_backend.services;

import cz.upce.nnpro_stk_backend.entities.BranchOffice;

import cz.upce.nnpro_stk_backend.entities.User;
import cz.upce.nnpro_stk_backend.dtos.BranchOfficeIdUserIdDto;
import cz.upce.nnpro_stk_backend.dtos.BranchOfficeInDto;
import cz.upce.nnpro_stk_backend.dtos.UserDetailOutDto;
import cz.upce.nnpro_stk_backend.repositories.BranchOfficeRepository;

import cz.upce.nnpro_stk_backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BranchOfficeService {
    private final BranchOfficeRepository branchOfficeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public BranchOfficeService(BranchOfficeRepository branchOfficeRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.branchOfficeRepository = branchOfficeRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public BranchOffice addOffice(BranchOfficeInDto branchOfficeDto) {
        if (branchOfficeRepository.existsByRegionAndDistrictAndCity(branchOfficeDto.getRegion(), branchOfficeDto.getDistrict(), branchOfficeDto.getCity())) {
            throw new IllegalArgumentException("The branch office already exists.");
        }
        BranchOffice branchOffice = new BranchOffice();
        branchOffice.setRegion(branchOfficeDto.getRegion());
        branchOffice.setDistrict(branchOfficeDto.getDistrict());
        branchOffice.setCity(branchOfficeDto.getCity());
        BranchOffice save = branchOfficeRepository.save(branchOffice);
        return save;
    }

    public BranchOffice removeOffice(Long officeId) {
        BranchOffice branchOffice = branchOfficeRepository.findById(officeId).orElseThrow(() -> new NoSuchElementException("Branch office not found!"));
        userRepository.setUserOfficeToNUllByOffice(branchOffice);
        branchOfficeRepository.deleteById(officeId);
        return branchOffice;
    }

    public BranchOffice editOffice(Long officeId, BranchOfficeInDto officeDto) {

        BranchOffice branchOffice = branchOfficeRepository.findById(officeId).orElseThrow(() -> new NoSuchElementException("Branch office not found!"));
        if (branchOfficeRepository.existsByRegionAndDistrictAndCityAndIdIsNot(officeDto.getRegion(), officeDto.getDistrict(), officeDto.getCity(), officeId)) {
            throw new IllegalArgumentException("The branch office already exists.");
        }
        branchOffice.setRegion(officeDto.getRegion());
        branchOffice.setDistrict(officeDto.getDistrict());
        branchOffice.setCity(officeDto.getCity());
        BranchOffice save = branchOfficeRepository.save(branchOffice);
        return save;
    }

    public BranchOffice getOffice(Long officeId) {
        BranchOffice branchOffice = branchOfficeRepository.findById(officeId).orElseThrow(() -> new NoSuchElementException("Branch office not found!"));
        return branchOffice;
    }

    public UserDetailOutDto addUserToOffice(BranchOfficeIdUserIdDto branchOfficeIdUserIdDto) {

        User user = userRepository.findById(branchOfficeIdUserIdDto.getUserId()).orElseThrow(() -> new NoSuchElementException("User not found!"));
        user.setBranchOffice(branchOfficeRepository.findById(branchOfficeIdUserIdDto.getBranchOfficeId()).orElseThrow(() -> new NoSuchElementException("Branch office not found!")));
        User save = userRepository.save(user);
        //todo: otestovat
        //        UserDetailOutDto detailOutDto = ConversionService.convertToUserDetailOutDto(save);
        UserDetailOutDto detailOutDto = modelMapper.map(save,UserDetailOutDto.class);
        return detailOutDto;
    }

   /* public UserDetailOutDto removeUserFromOffice(Long userId, Long branchId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found!"));
        BranchOffice branchOffice = branchOfficeRepository.findById(branchId).orElseThrow(() -> new NoSuchElementException("Branch office not found!");

        if(user.getBranchOffice().getId()!=branchOffice)

                user.setBranchOffice(branchOfficeRepository.findById(branchOfficeIdUserIdDto.getBranchOfficeId()).orElseThrow(() -> new NoSuchElementException("Branch office not found!")));
        User save = userRepository.save(user);
        //todo: otestovat
        //        UserDetailOutDto detailOutDto = ConversionService.convertToUserDetailOutDto(save);
        UserDetailOutDto detailOutDto = modelMapper.map(save,UserDetailOutDto.class);
        return detailOutDto;
    }*/

    public List<BranchOffice> getAllOffices() {
        List<BranchOffice> branchOffices = branchOfficeRepository.findAll();
        return branchOffices;
    }
}
