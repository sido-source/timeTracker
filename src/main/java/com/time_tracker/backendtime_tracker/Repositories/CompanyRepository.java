package com.time_tracker.backendtime_tracker.Repositories;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Entities.Contract;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    @Override
    Iterable<Company> findAll();
    @Override
    Optional<Company> findById(Long companyId);
    @Override
    void deleteById(Long integer);
    @Override
    Company save(Company company);

    Optional<Company> getCompanyById(Long companyId);

//    @Query("SELECT Contract.id FROM Contract c WHERE c.company.id = :companyId")
//    Optional<Company> findCompanyInContract(@Param("name") Long companyId);
//
//    @Query("DELETE from Contractor where id = :contractorId")
//    void deleteContract(@Param("contractId") Long contractId);

}
