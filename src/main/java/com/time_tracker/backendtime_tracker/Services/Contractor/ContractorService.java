package com.time_tracker.backendtime_tracker.Services.Contractor;

import com.time_tracker.backendtime_tracker.Entities.Contractor;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface ContractorService {
    public Contractor updateContractor(Contractor contractor) throws Exception;

    public void deleteContractor(Integer id) throws Exception;

    public Contractor saveContractor(Contractor contractor) throws Exception;

    public Contractor getSpecificContractor(Integer contractorId) throws Exception;

    public Set<Contractor> getAllContractors();
}
