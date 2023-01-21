package com.time_tracker.backendtime_tracker.Repositories;

import com.time_tracker.backendtime_tracker.Entities.Contract;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractorRepository extends CrudRepository<Contractor, Long> {
    Optional<Contractor> findById(Long contractId);
}
