package com.time_tracker.backendtime_tracker.Mapper;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDtoDetails;
import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDtoWithContracts;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Entities.Contract;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompanyMapper {

    public static CompanyDto castCompanyToCompanyDTO(Company company){
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setBudget(company.getBudget());
        companyDto.setIndustry(company.getIndustry());
        companyDto.setFoundedYear(company.getFoundedYear());

        return companyDto;
    }

    public static Company castCompanyDtoToCompany(CompanyDto companyDto){
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setBudget(companyDto.getBudget());
        company.setIndustry(companyDto.getIndustry());
        company.setFoundedYear(companyDto.getFoundedYear());

        return company;
    }

    public static Set<CompanyDto> castIterableToCompanyDtoSet(Iterable<Company> iterable){
        Set<CompanyDto> companySet = new HashSet<>();

        for (Company company : iterable) {
            companySet.add(CompanyMapper.castCompanyToCompanyDTO(company));
        }
        return companySet;
    }

    public static Company updateCompanyFields(Company oldCompany, Company newCompany){
        Company resultCompany = new Company();

        resultCompany.setId(oldCompany.getId());

        String newName = (newCompany.getName() != null)? newCompany.getName() : oldCompany.getName();
        resultCompany.setName(newName);

        Integer newBuget = (newCompany.getBudget() != null)? newCompany.getBudget(): oldCompany.getBudget();
        resultCompany.setBudget(newBuget);

        Integer newYear = (newCompany.getFoundedYear() != null)? newCompany.getFoundedYear() : oldCompany.getFoundedYear();
        resultCompany.setFoundedYear(newYear);

        String newIndustry = (newCompany.getIndustry() != null)? newCompany.getIndustry() : oldCompany.getIndustry();
        resultCompany.setIndustry(newIndustry);


        return resultCompany;
    }

    public static CompanyDtoWithContracts castProjectToCompanyDtoWithProject(Contract contract){
        CompanyDtoWithContracts companyDtoWithContracts = new CompanyDtoWithContracts();

        companyDtoWithContracts.setContractId(contract.getId());
        companyDtoWithContracts.setContractDescription(contract.getDescription());
        companyDtoWithContracts.setContractorDailySalary(contract.getDailySalary());
        companyDtoWithContracts.setContractStartDate(contract.getStartDate());
        companyDtoWithContracts.setContractEndDate(contract.getEndDate());
        companyDtoWithContracts.setContractorName(contract.getContractor().getName());
        companyDtoWithContracts.setContractorSurname(contract.getContractor().getSurname());
        companyDtoWithContracts.setContractorPosition(contract.getContractor().getPosition());
        companyDtoWithContracts.setContractorPesel(contract.getContractor().getPesel());

        return companyDtoWithContracts;
    }

    public static CompanyDtoDetails castCompanyToCompanyDtoDetails(Company company){
        CompanyDto companyDto = new CompanyDto(company.getId(), company.getName(), company.getIndustry(), company.getFoundedYear(), company.getBudget());
        Set<CompanyDtoWithContracts> companyDtoWithContractsSet = new HashSet<>();

        for(Contract contract : company.getContracts()){
            companyDtoWithContractsSet.add(castProjectToCompanyDtoWithProject(contract));
        }

        return new CompanyDtoDetails(companyDto, companyDtoWithContractsSet);

    }
}
