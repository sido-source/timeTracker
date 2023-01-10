package com.time_tracker.backendtime_tracker.Mapper;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Entities.Company;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompanyMapper {

    public static CompanyDto mappedToCompanyDTO(Company company){
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(company.getName());
        companyDto.setBudget(company.getBudget());
        companyDto.setIndustry(company.getIndustry());
        companyDto.setFoundedYear(company.getFoundedYear());

        return companyDto;
    }

    public static Company mappedToCompany(CompanyDto companyDto){
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setBudget(companyDto.getBudget());
        company.setIndustry(companyDto.getIndustry());
        company.setFoundedYear(companyDto.getFoundedYear());

        return company;
    }

    public static Set<Company> castIterableToSet(Iterable<Company> iterable){
        Set<Company> companySet = new HashSet<Company>();
        for (Company company : iterable) {
            companySet.add(company);
        }
        return companySet;
    }

    public static Company updateCompanyFields(Company oldCompany, Company newCompany){
        Company resultCompany = new Company();

        resultCompany.setId(oldCompany.getId());

        String newName = (newCompany.getName() != null)? newCompany.getName() : oldCompany.getName();
        resultCompany.setName(newName);

        Integer newBuget = (newCompany.getBudget()!=0 && newCompany.getBudget() != null)? newCompany.getBudget(): oldCompany.getBudget();
        resultCompany.setBudget(newBuget);

        Integer newYear = (newCompany.getFoundedYear() != null && newCompany.getFoundedYear() != 0)? newCompany.getFoundedYear() : oldCompany.getFoundedYear();
        resultCompany.setFoundedYear(newYear);

        String newIndustry = (newCompany.getIndustry() != null)? newCompany.getIndustry() : oldCompany.getIndustry();
        resultCompany.setIndustry(newIndustry);


        return resultCompany;
    }
}
