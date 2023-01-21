package com.time_tracker.backendtime_tracker.Repositories;

import com.time_tracker.backendtime_tracker.Entities.Contract;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {
    Iterable<Contract> findByCompanyId(Long id);
    Iterable<Contract> findByContractorId(Long id);
}
