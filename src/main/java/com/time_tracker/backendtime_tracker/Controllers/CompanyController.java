package com.time_tracker.backendtime_tracker.Controllers;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDtoDetails;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Services.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/api/company/")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    public CompanyController(){

    }

    @PostMapping("save")
    public ResponseEntity<CompanyDto> saveCompany(@Valid @RequestBody CompanyDto companyDto){

        try {
            companyDto = companyService.saveCompany(companyDto);
        } catch (Exception e) {
            //throw new RuntimeException(e);

            return new ResponseEntity<CompanyDto>(companyDto, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<CompanyDto>(companyDto, HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<Set<CompanyDto>> getAllCompanies(){
        Set<CompanyDto> companySet = null;

        try {
            companySet = new HashSet<CompanyDto>(companyService.getAllCompanies());
        }catch (Exception e){
            return new ResponseEntity<Set<CompanyDto>>(companySet ,HttpStatus.OK);
        }
        return new ResponseEntity<Set<CompanyDto>>(companySet, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<String> updateCompany( @RequestBody CompanyDto companyDto){
        //if(companyService.add(companyDto)) "Succes" else "false"
        CompanyDto updatedCompany;

        try {
            updatedCompany = companyService.updateCompany(companyDto);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Succesfully updated", HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("delete/{name}")
    public ResponseEntity<String> deleteCompany(@PathVariable("name") String companyName){

        try{
            companyService.deleteCompany(companyName);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @GetMapping("get/{name}")
    public ResponseEntity<CompanyDtoDetails> getSpecificCompany(@PathVariable("name") String companyName) throws Exception {

        CompanyDtoDetails companyDtoDetails = null;
        try{
            companyDtoDetails = companyService.getSpecificCompany(companyName);
        }catch(Exception e){
            return new ResponseEntity<CompanyDtoDetails>(companyDtoDetails, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<CompanyDtoDetails>(companyDtoDetails, HttpStatus.OK);

    }
}
