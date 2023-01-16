package com.time_tracker.backendtime_tracker.Controllers;

import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDto;
import com.time_tracker.backendtime_tracker.Dtos.Contractor.ContractorDtoDetails;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Mapper.ContractorMapper;
import com.time_tracker.backendtime_tracker.Repositories.ContractorRepository;
import com.time_tracker.backendtime_tracker.Services.Contractor.ContractorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/contractor")
public class ContractorController {

    @Autowired
    private ContractorServiceImpl contractorService;

    @Autowired
    private ContractorRepository contractorRepository;

    @PostMapping("update")
    public ResponseEntity<ContractorDto> updateContractor(@RequestBody ContractorDto contractor){
        ContractorDto resultContractor =null;
        try {
            resultContractor = contractorService.updateContractor(contractor);
        }catch (Exception e){
            return new ResponseEntity<ContractorDto>(resultContractor, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ContractorDto>(resultContractor, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContractor(Long contractorId){

        try{
            contractorService.deleteContractor(contractorId);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<Set<ContractorDto>> getAllContractors(){
        Set<ContractorDto> contractorSet = new HashSet<>();

        try{
            contractorSet = contractorService.getAllContractors();
        }catch (Exception e){
            return new ResponseEntity<Set<ContractorDto>>(contractorSet ,HttpStatus.OK);
        }
        return new ResponseEntity<Set<ContractorDto>>(contractorSet, HttpStatus.OK);
    }

    @GetMapping("get/{contractorId}")
    public ResponseEntity<ContractorDtoDetails> getSpecificContractor(@PathVariable Long contractorId){

        ContractorDtoDetails contractorDtoDetails = null;

        try{
            contractorDtoDetails = contractorService.getSpecificContractor(contractorId);
            return new ResponseEntity<ContractorDtoDetails>(contractorDtoDetails, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<ContractorDtoDetails>(contractorDtoDetails, HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/save")
    public ResponseEntity<ContractorDto> saveContractor(@RequestBody ContractorDto contractor){
        ContractorDto resultContractor = null;

        try {
            resultContractor = contractorService.saveContractor(contractor);
        } catch (Exception e){
            return new ResponseEntity<ContractorDto>(resultContractor, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ContractorDto>(resultContractor, HttpStatus.OK);
    }

    @GetMapping("/test/get/{id}")
    public ResponseEntity<ContractorDto> test(@PathVariable Long id){
        Optional<Contractor> contractor = contractorRepository.findById(id);
        return new ResponseEntity<ContractorDto>(ContractorMapper.castContractToContractorDto(contractor.get()), HttpStatus.OK);
    }
}
