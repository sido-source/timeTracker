package com.time_tracker.backendtime_tracker.Controllers;

import com.time_tracker.backendtime_tracker.Dtos.Contract.ContractDto;
import com.time_tracker.backendtime_tracker.Dtos.Contract.ContractDtoDetails;
import com.time_tracker.backendtime_tracker.Entities.Contract;
import com.time_tracker.backendtime_tracker.Repositories.ContractRepository;
import com.time_tracker.backendtime_tracker.Services.Contract.ContractService;
import com.time_tracker.backendtime_tracker.Services.Contract.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    @Autowired
    private ContractServiceImpl contractServiceImpl;
    @Autowired
    private ContractRepository contractRepository;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) throws Exception {

        try {
            contractServiceImpl.deleteContract(id);
        } catch (Exception e) {
            new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<Set<ContractDto>> getAllProjects(){
        Set<ContractDto> contractDto = null;
        contractDto = contractServiceImpl.getAllContracts();
        return new ResponseEntity<Set<ContractDto>>(contractDto, HttpStatus.OK);
    }

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
}
