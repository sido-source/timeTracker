package com.time_tracker.backendtime_tracker.Controllers;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDtoDetails;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Mapper.ContractorMapper;
import com.time_tracker.backendtime_tracker.Repositories.ContractorRepository;
import com.time_tracker.backendtime_tracker.Services.Contractor.ContractorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/contractor")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}, allowCredentials = "true")
public class ContractorController {

    @Autowired
    private ContractorServiceImpl contractorServiceImpl;

    @Autowired
    private ContractorRepository contractorRepository;
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'COMPANY')")
    @PostMapping("update/{id}")
    public ResponseEntity<ContractorDto> updateContractor(@PathVariable Long id, @RequestBody ContractorDto contractorDto){
        ContractorDto resultContractor =null;


        try {
            resultContractor = contractorServiceImpl.updateContractor(id, contractorDto);
        }catch (Exception e){
            return new ResponseEntity<ContractorDto>(resultContractor, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ContractorDto>(resultContractor, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContractor(@PathVariable Long id){

        try{
            contractorServiceImpl.deleteContractor(id);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("get")
    public ResponseEntity<Set<ContractorDto>> getAllContractors(){
        Set<ContractorDto> contractorSet = new HashSet<ContractorDto>();

        try{
            contractorSet = contractorServiceImpl.getAllContractors();
        }catch (Exception e){
            return new ResponseEntity<Set<ContractorDto>>(contractorSet ,HttpStatus.OK);
        }
        return new ResponseEntity<Set<ContractorDto>>(contractorSet, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("get/{contractorId}")
    public ResponseEntity<ContractorDtoDetails> getSpecificContractor(@PathVariable Long contractorId){

        ContractorDtoDetails contractorDtoDetails = null;

        try{
            contractorDtoDetails = contractorServiceImpl.getSpecificContractor(contractorId);
            return new ResponseEntity<ContractorDtoDetails>(contractorDtoDetails, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<ContractorDtoDetails>(contractorDtoDetails, HttpStatus.BAD_REQUEST);
        }


    }

    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<ContractorDto> saveContractor(@RequestBody ContractorDto contractor){

        // generate username, password, roles
        ContractorDto resultContractor = null;

        try {
            resultContractor = contractorServiceImpl.saveContractor(contractor);
        } catch (Exception e){
            return new ResponseEntity<ContractorDto>(resultContractor, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ContractorDto>(resultContractor, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN', 'USER')")
    @GetMapping("getContractors/forCompany/{companyId}")
    public ResponseEntity<Set<ContractorDto>> getAllContractorsForCompany(@PathVariable("companyId") Long contractorId) throws Exception {
        return new ResponseEntity<Set<ContractorDto>>(contractorServiceImpl.getAllContractorsForCompany(contractorId), HttpStatus.OK);
    }

    @GetMapping("/test/get/{id}")
    public ResponseEntity<ContractorDto> test(@PathVariable Long id){
        Optional<Contractor> contractor = contractorRepository.findById(id);
        return new ResponseEntity<ContractorDto>(ContractorMapper.castContractorToContractorDto(contractor.get()), HttpStatus.OK);
    }
}
