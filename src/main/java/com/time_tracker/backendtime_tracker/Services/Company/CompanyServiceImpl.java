package com.time_tracker.backendtime_tracker.Services.Company;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
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

    public CompanyServiceImpl(){
//        try {
//            companyRepository.save(new Company(null,"n1", "i1", 1,1));
//            companyRepository.save(new Company(null,"n2", "i2", 2,2));
//            companyRepository.save(new Company(null,"n3", "i3", 3,3));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }
    @Override
    public CompanyDto saveCompany(CompanyDto companyDto) throws Exception {
        //mapping dto model -> entity model
        Company newCompany = CompanyMapper.mappedToCompany(companyDto);
        if(companyRepository.findByName(companyDto.getName()).isPresent()){
            throw new Exception("Given company name already exists");
        }
        //save company
        try{
            newCompany = companyRepository.save(newCompany);
        }catch(DataAccessException e){
            throw new Exception(e.getCause());
        }
        return CompanyMapper.mappedToCompanyDTO(newCompany);
    }

    @Override
    public void deleteCompany(String companyName) throws Exception {
        Optional<Company> returnedCompany = companyRepository.findByName(companyName);

        if(returnedCompany.isPresent()){
            companyRepository.deleteById(returnedCompany.get().getId());
        }else{
            throw new Exception("Given company name does not exist");
        }

    }

    @Override
    public Set<Company> getAllCompanies() {
        Iterable<Company> allCompanies = companyRepository.findAll();
        return castIterableToSet(allCompanies);
    }

    @Override
    public CompanyDto getSpecificCompany(String companyName) throws Exception {

        Optional<Company> returnedCompany = companyRepository.findByName(companyName);

        if(!(returnedCompany.isPresent())){
            throw new Exception("Given company name does not exist");
        }

        return CompanyMapper.mappedToCompanyDTO(returnedCompany.get());
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) throws Exception {
        Company returnedCompany = null;
        Integer id;

        if(!(companyRepository.findByName(companyDto.getName()).isPresent())){
            throw new Exception("Given company name does not exists");
        }else {
            returnedCompany = companyRepository.findByName(companyDto.getName()).get();
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
