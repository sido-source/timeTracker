package com.time_tracker.backendtime_tracker.Controllers;

import com.time_tracker.backendtime_tracker.Dtos.Contract.ContractDto;
import com.time_tracker.backendtime_tracker.Dtos.Contract.ContractDtoDetails;
import com.time_tracker.backendtime_tracker.Entities.Contract;
import com.time_tracker.backendtime_tracker.Repositories.CompanyRepository;
import com.time_tracker.backendtime_tracker.Repositories.ContractRepository;
import com.time_tracker.backendtime_tracker.Services.Contract.ContractService;
import com.time_tracker.backendtime_tracker.Services.Contract.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}, allowCredentials = "true")
public class ContractController {

    @Autowired
    private ContractServiceImpl contractServiceImpl;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    @PostMapping("update/{id}")
    public ResponseEntity<ContractDto> updateProject(@PathVariable Long id, @RequestBody ContractDto contractDto) throws Exception {
        ContractDto contractDtoResult = new ContractDto();

        try{
            contractDtoResult = contractServiceImpl.updateContract(id,contractDto);
        } catch (Exception e) {
            return new ResponseEntity<ContractDto>(contractDtoResult, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ContractDto>(contractDtoResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN', 'USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) throws Exception {

        try {
            contractServiceImpl.deleteContract(id);
        } catch (Exception e) {
            new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("get")
    public ResponseEntity<Set<ContractDto>> getAllProjects(){
        Set<ContractDto> contractDto = null;
        contractDto = contractServiceImpl.getAllContracts();
        return new ResponseEntity<Set<ContractDto>>(contractDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN', 'USER')")
    @GetMapping("get/{id}")
    public ResponseEntity<Contract> getSpecificProject(@PathVariable Long id) throws Exception {

        Contract contract = null;

        try {
            contract = contractServiceImpl.getSpecificContract(id);
        } catch (Exception e) {
            return new ResponseEntity<Contract>(contract, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Contract>(contract, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Contract> saveProject(@RequestBody ContractDto contractDto){
        Contract contractResuit = null;

        try {
            contractResuit = contractServiceImpl.saveContract(contractDto);
        } catch (Exception e) {
            return new ResponseEntity<Contract>(contractResuit, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Contract>(contractResuit, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/getContracts/forContractor/{contractorId}")
    public ResponseEntity<Set<ContractDto>> getContractForContractor(@PathVariable Long contractorId){
        return new ResponseEntity<Set<ContractDto>>(contractServiceImpl.getAllContractsForContractor(contractorId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    @GetMapping("/getContracts/forCompany/{contractorId}")
    public ResponseEntity<Set<ContractDto>> getContractForCompany(@PathVariable Long companyId){
        return new ResponseEntity<Set<ContractDto>>(contractServiceImpl.getAllContractsForCompany(companyId), HttpStatus.OK);
    }
}
