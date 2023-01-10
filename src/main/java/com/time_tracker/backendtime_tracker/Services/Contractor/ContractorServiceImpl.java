package com.time_tracker.backendtime_tracker.Services.Contractor;

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
    public Contractor updateContractor(Contractor contractor) throws Exception {
        Contractor updateContractor = new Contractor();
        Optional<Contractor> resultContractor = contractorRepository.findById(contractor.getId());

        if(!(resultContractor.isPresent())){
            throw new Exception("Contractor with given id does not exist");
        }

        updateContractor = ContractorMapper.updateCompanyFields(resultContractor.get(), contractor);

        try{
            contractorRepository.save(updateContractor);
        }catch (Exception e){
            throw new Exception(e.getCause().getCause());
        }

        return updateContractor;
    }

    public void deleteContractor(Integer contractorId) throws Exception {
        Optional<Contractor> contractor = contractorRepository.findById(contractorId);

        if(!(contractor.isPresent())){
            throw new Exception("Contractor with given id does not exist");
        }

        contractorRepository.deleteById(contractorId);

    }

    public Contractor saveContractor(Contractor contractor) throws Exception {
        //meybe in the future there will be veryfication if the contractor alredy exists in the db
        // for now only verification is using annotation in entity class
        Contractor resultedContractor = null;
        try {
            resultedContractor = new Contractor();
            resultedContractor = contractorRepository.save(contractor);
        }catch (Exception e){
            throw new Exception(e.getCause());
        }

        return resultedContractor;
    }

    public Contractor getSpecificContractor(Integer contractorId) throws Exception {
        Optional<Contractor> contractor = contractorRepository.findById(contractorId);

        if(!(contractor.isPresent())){
            throw new Exception("Contractor with given id does not exist");
        }
        return contractor.get();
    }

    public Set<Contractor> getAllContractors(){
        Iterable<Contractor> contractors = contractorRepository.findAll();
        return ContractorMapper.castIterableCompanyToSet(contractors);
    }

}
