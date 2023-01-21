package com.time_tracker.backendtime_tracker.Services.Company;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDtoDetails;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Mapper.CompanyMapper;
import com.time_tracker.backendtime_tracker.Repositories.CompanyRepository;
import com.time_tracker.backendtime_tracker.Repositories.ContractRepository;
import com.time_tracker.backendtime_tracker.Repositories.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static com.time_tracker.backendtime_tracker.Mapper.CompanyMapper.castIterableToCompanyDtoSet;

//This class uses DTO that's why id is not given, for instance: to client or user

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ContractRepository contractRepository;

    @Override
    public CompanyDto saveCompany(CompanyDto companyDto) throws Exception {

        Company newCompany = CompanyMapper.castCompanyDtoToCompany(companyDto);

        if(companyRepository.getCompanyById(companyDto.getId()).isPresent()){
            throw new Exception("Given company name already exists");
        }

        try{
            newCompany = companyRepository.save(newCompany);
        }catch(Exception e){
            throw new Exception(e.getCause());
        }
        return CompanyMapper.castCompanyToCompanyDTO(newCompany);
    }

    @Override
    public void deleteCompany(Long companyId) throws Exception {
        Optional<Company> returnedCompany = companyRepository.getCompanyById(companyId);

        if(returnedCompany.isPresent()){
//            if(companyRepository.findCompanyInContract(companyId).isPresent()){
//                companyRepository.deleteContract(companyRepository.findCompanyInContract(companyId).get().getId());
//                companyRepository.deleteById(companyId);
//            }
            contractRepository.deleteAll(contractRepository.findByCompanyId(companyId));
            //companyRepository.deleteById(companyId);
        }else{
            throw new Exception("Given company name does not exist");
        }

    }

    @Override
    public Set<CompanyDto> getAllCompanies() {
        Iterable<Company> allCompanies = companyRepository.findAll();
        return castIterableToCompanyDtoSet(allCompanies);
    }

    @Override
    public CompanyDtoDetails getSpecificCompany(Long companyId) throws Exception {

        Optional<Company> returnedCompany = companyRepository.getCompanyById(companyId);

        if(!(returnedCompany.isPresent())){
            throw new Exception("Given company name does not exist");
        }

        return CompanyMapper.castCompanyToCompanyDtoDetails(returnedCompany.get());
    }

    @Override
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) throws Exception {
        Company returnedCompany = null;

        if(!(companyRepository.getCompanyById(id).isPresent())){
            throw new Exception("Given company name does not exists");
        }else {
            returnedCompany = companyRepository.getCompanyById(id).get();
        }

        returnedCompany = CompanyMapper.updateCompanyFields(returnedCompany, CompanyMapper.castCompanyDtoToCompany(companyDto));

        try {
            returnedCompany = companyRepository.save(returnedCompany);
        }catch (Exception e){
            throw new Exception(e.getCause().getCause());
        }

        return CompanyMapper.castCompanyToCompanyDTO(returnedCompany);
    }
}
