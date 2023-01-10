package com.time_tracker.backendtime_tracker.Controllers;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Consultants.ContractorDto;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Mapper.ContractorMapper;
import com.time_tracker.backendtime_tracker.Services.Contractor.ContractorService;
import com.time_tracker.backendtime_tracker.Services.Contractor.ContractorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/contractor")
public class ContractorController {

    @Autowired
    private ContractorServiceImpl contractorService;

    @PostMapping("update")
    public ResponseEntity<Contractor> updateContractor(@RequestBody Contractor contractor){
        Contractor resultContractor =null;
        try {
            resultContractor = contractorService.updateContractor(contractor);
        }catch (Exception e){
            return new ResponseEntity<Contractor>(resultContractor, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Contractor>(resultContractor, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContractor(Integer contractorId){

        try{
            contractorService.deleteContractor(contractorId);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<Set<Contractor>> getAllContractors(){
        Set<Contractor> contractorSet = new HashSet<>();

        try{
            contractorSet = contractorService.getAllContractors();
        }catch (Exception e){
            return new ResponseEntity<Set<Contractor>>(contractorSet ,HttpStatus.OK);
        }
        return new ResponseEntity<Set<Contractor>>(contractorSet, HttpStatus.OK);
    }

    @GetMapping("get/{contractorId}")
    public ResponseEntity<ContractorDto> getSpecificContractor(@PathVariable Integer contractorId){

        Contractor contractor = null;

        try{
            contractor = contractorService.getSpecificContractor(contractorId);
            return new ResponseEntity<ContractorDto>(ContractorMapper.mapProject(contractor), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<ContractorDto>(ContractorMapper.mapProject(contractor), HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/save")
    public ResponseEntity<Contractor> saveContractor(@RequestBody Contractor contractor){
        Contractor resultContractor = null;

        try {
            resultContractor = contractorService.saveContractor(contractor);
        } catch (Exception e){
            return new ResponseEntity<Contractor>(resultContractor, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Contractor>(resultContractor, HttpStatus.OK);
    }
}
