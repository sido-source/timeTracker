package com.time_tracker.backendtime_tracker.Services.Contractor;

import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDtoDetails;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Mapper.CompanyMapper;
import com.time_tracker.backendtime_tracker.Mapper.ContractorMapper;
import com.time_tracker.backendtime_tracker.Repositories.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ContractorServiceImpl implements ContractorService{

    @Autowired
    private ContractorRepository contractorRepository;
    @Override
    public ContractorDto updateContractor(ContractorDto contractorDto) throws Exception {
        Contractor updateContractor = new Contractor();

        Optional<Contractor> resultContractor = contractorRepository.findById(contractorDto.getId());

        if(!(resultContractor.isPresent())){
            throw new Exception("Contractor with given id does not exist");
        }

        updateContractor = ContractorMapper.updateCompanyFields(resultContractor.get(), ContractorMapper.castContractorDtoToContractor(contractorDto));

        try{
            contractorRepository.save(updateContractor);
        }catch (Exception e){
            throw new Exception(e.getCause().getCause());
        }

        return ContractorMapper.castContractToContractorDto(updateContractor);
    }
    @Override
    public void deleteContractor(Long contractorId) throws Exception {
        Optional<Contractor> contractor = contractorRepository.findById(contractorId);

        if(!(contractor.isPresent())){
            throw new Exception("Contractor with given id does not exist");
        }

        contractorRepository.deleteById(contractorId);

    }

    @Override
    public ContractorDto saveContractor(ContractorDto contractor) throws Exception {
        //meybe in the future there will be veryfication if the contractor alredy exists in the db
        // for now only verification is using annotation in entity class
        Contractor resultedContractor = null;
        try {
            resultedContractor = contractorRepository.save(ContractorMapper.castContractorDtoToContractor(contractor));
        }catch (Exception e){
            throw new Exception(e.getCause());
        }

        return ContractorMapper.castContractToContractorDto(resultedContractor);
    }

    @Override
    public ContractorDtoDetails getSpecificContractor(Long contractorId) throws Exception {
        Optional<Contractor> contractor = contractorRepository.findById(contractorId);

        if(!(contractor.isPresent())){
            throw new Exception("Contractor with given id does not exist");
        }

        return ContractorMapper.castContractorToContractorDtoDetails(contractor.get());
    }

    @Override
    public Set<ContractorDto> getAllContractors(){
        Iterable<Contractor> contractors = contractorRepository.findAll();
        return ContractorMapper.castIterableCompanyToSet(contractors);
    }

}
