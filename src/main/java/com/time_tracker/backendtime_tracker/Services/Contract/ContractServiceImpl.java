package com.time_tracker.backendtime_tracker.Services.Contract;

import com.time_tracker.backendtime_tracker.Dtos.Contract.ContractDto;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Entities.Contract;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Mapper.ContractMapper;
import com.time_tracker.backendtime_tracker.Repositories.CompanyRepository;
import com.time_tracker.backendtime_tracker.Repositories.ContractorRepository;
import com.time_tracker.backendtime_tracker.Repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public ContractDto updateContract(Long contractorId, ContractDto contractorDto) throws Exception {
        Optional<Contractor> contractor = null;
        Optional<Company> company = null;
        ContractDto updateContractDto = null;

        if(!(contractRepository.findById(contractorId).isPresent())){
            throw new Exception("Contract with given id does not exist");
        }else{
            updateContractDto = ContractMapper.castContractToContractDto(contractRepository.findById(contractorId).get());
        }

        if(contractorDto.getContractorId() != null) {
            contractor = contractorRepository.findById(contractorDto.getContractId());
            if(!(contractor.isPresent())){
                throw new Exception("Contractor with given id does not exist");
            }
        }

        if(contractorDto.getCompanyId() != null){
            company = companyRepository.findById(contractorDto.getCompanyId());
            if(!(company.isPresent())){
                throw new Exception("Given company name does not exist");
            }
        }


        updateContractDto = ContractMapper.updateContractDtoFields(updateContractDto, contractorDto);


        try{
            contractRepository.save(ContractMapper.castContractDtoToContract(updateContractDto, company.get(), contractor.get()));
        }catch (Exception e){
            throw new Exception(e.getCause().getCause());
        }

        return updateContractDto;
    }

    @Override
    public void deleteContract(Long contractId) throws Exception {
        Optional<Contract> contractor = contractRepository.findById(contractId);

        if(!(contractor.isPresent())){
            throw new Exception("Project with given id does not exist");
        }

        contractRepository.deleteById(contractId);

    }

    @Override
    public Contract saveContract(ContractDto contractDto) throws Exception {
        Contract resultedContract = null;

        Optional<Contractor> contractor = contractorRepository.findById(contractDto.getContractorId());
        if(!(contractor.isPresent())){
            throw new Exception("Contractor with given id does not exist");
        }

        Optional<Company> company = companyRepository.findById(contractDto.getCompanyId());
        if(!(company.isPresent())){
            throw new Exception("Given company name does not exist");
        }

        resultedContract = ContractMapper.castContractDtoToContract(contractDto,
                company.get(), contractor.get());

        try {
            resultedContract = contractRepository.save(resultedContract);
        }catch (Exception e){
            throw new Exception(e.getCause());
        }

        return resultedContract;
    }

    @Override
    public Contract getSpecificContract(Long id) throws Exception {
        Optional<Contract> contract = contractRepository.findById(id);

        if(!(contract.isPresent())){
            throw new Exception("Project with given id does not exist");
        }

        return contract.get();
    }

    @Override
    public Set<ContractDto> getAllContracts() {
        Iterable<Contract> contractors = contractRepository.findAll();
        return ContractMapper.castIterableToContractDtoSet(contractors);
    }
}
