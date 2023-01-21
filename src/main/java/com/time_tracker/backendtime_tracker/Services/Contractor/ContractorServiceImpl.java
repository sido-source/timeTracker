package com.time_tracker.backendtime_tracker.Services.Contractor;

import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDtoDetails;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Mapper.ContractorMapper;
import com.time_tracker.backendtime_tracker.Repositories.ContractRepository;
import com.time_tracker.backendtime_tracker.Repositories.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ContractorServiceImpl implements ContractorService{

    @Autowired
    private ContractorRepository contractorRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Override
    public ContractorDto updateContractor(Long contractId, ContractorDto contractorDto) throws Exception {
        Contractor updateContractor = new Contractor();

        Optional<Contractor> resultContractor = contractorRepository.findById(contractId);

        if(!(resultContractor.isPresent())){
            throw new Exception("Contractor with given id does not exist");
        }


        if(contractorDto.getPesel() != resultContractor.get().getPesel() && contractorDto.getPesel() != null){
            throw new Exception("Change the pesel is forbidden");
        }

        updateContractor = ContractorMapper.updateCompanyFields(resultContractor.get(), ContractorMapper.castContractorDtoToContractor(contractorDto));

        try{
            contractorRepository.save(updateContractor);
        }catch (Exception e){
            throw new Exception(e.getCause().getCause());
        }

        return ContractorMapper.castContractorToContractorDto(updateContractor);
    }
    @Override
    public void deleteContractor(Long contractorId) throws Exception {
        Optional<Contractor> contractor = contractorRepository.findById(contractorId);

        if(!(contractor.isPresent())){
            throw new Exception("Contractor with given id does not exist");
        }else {
            contractRepository.deleteAll(contractRepository.findByContractorId(contractorId));
        }

        //contractorRepository.deleteById(contractorId);

    }

    @Override
    public ContractorDto saveContractor(ContractorDto contractorDto) throws Exception {
        //meybe in the future there will be veryfication if the contractor alredy exists in the db
        // for now only verification is using annotation in entity class
        Contractor resultedContractor = null;
        try {
            resultedContractor = contractorRepository.save(ContractorMapper.castContractorDtoToContractor(contractorDto));
        }catch (Exception e){
            throw new Exception(e.getCause());
        }

        return ContractorMapper.castContractorToContractorDto(resultedContractor);
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
        return ContractorMapper.castIterableToContractorDtoSet(contractors);
    }

}
