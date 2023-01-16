package com.time_tracker.backendtime_tracker.Services.Company;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDtoDetails;
import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDtoWithProject;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Mapper.CompanyMapper;
import com.time_tracker.backendtime_tracker.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static com.time_tracker.backendtime_tracker.Mapper.CompanyMapper.castIterableToSet;

//This class uses DTO that's why id is not given, for instance: to client or user

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyDto saveCompany(CompanyDto companyDto) throws Exception {

        Company newCompany = CompanyMapper.mappedToCompany(companyDto);

        if(companyRepository.getCompanyByName(companyDto.getName()).isPresent()){
            throw new Exception("Given company name already exists");
        }

        try{
            newCompany = companyRepository.save(newCompany);
        }catch(Exception e){
            throw new Exception(e.getCause());
        }
        return CompanyMapper.mappedToCompanyDTO(newCompany);
    }

    @Override
    public void deleteCompany(String companyName) throws Exception {
        Optional<Company> returnedCompany = companyRepository.getCompanyByName(companyName);

        if(returnedCompany.isPresent()){
            companyRepository.deleteById(returnedCompany.get().getId());
        }else{
            throw new Exception("Given company name does not exist");
        }

    }

    @Override
    public Set<CompanyDto> getAllCompanies() {
        Iterable<Company> allCompanies = companyRepository.findAll();
        return castIterableToSet(allCompanies);
    }

    @Override
    public CompanyDtoDetails getSpecificCompany(String companyName) throws Exception {

        Optional<Company> returnedCompany = companyRepository.getCompanyByName(companyName);

        if(!(returnedCompany.isPresent())){
            throw new Exception("Given company name does not exist");
        }

        return CompanyMapper.castCompanyToCompanyDtoDetails(returnedCompany.get());
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) throws Exception {
        Company returnedCompany = null;
        Integer id;

        if(!(companyRepository.getCompanyByName(companyDto.getName()).isPresent())){
            throw new Exception("Given company name does not exists");
        }else {
            returnedCompany = companyRepository.getCompanyByName(companyDto.getName()).get();
        }

        returnedCompany = CompanyMapper.updateCompanyFields(returnedCompany, CompanyMapper.mappedToCompany(companyDto));

        try {
            returnedCompany = companyRepository.save(returnedCompany);
        }catch (Exception e){
            throw new Exception(e.getCause().getCause());
        }

        return CompanyMapper.mappedToCompanyDTO(returnedCompany);
    }
}
