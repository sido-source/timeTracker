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
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}, allowCredentials = "true")
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

    @PostMapping("update/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto){
        //if(companyService.add(companyDto)) "Succes" else "false"
        CompanyDto updatedCompany = null;

        try {
            updatedCompany = companyService.updateCompany(id, companyDto);
        }catch (Exception e){
            return new ResponseEntity<CompanyDto>(updatedCompany, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<CompanyDto>(updatedCompany, HttpStatus.OK);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long companyId){

        try{
            companyService.deleteCompany(companyId);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<CompanyDtoDetails> getSpecificCompany(@PathVariable("id") Long companyId) throws Exception {

        CompanyDtoDetails companyDtoDetails = null;
        try{
            companyDtoDetails = companyService.getSpecificCompany(companyId);
        }catch(Exception e){
            return new ResponseEntity<CompanyDtoDetails>(companyDtoDetails, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<CompanyDtoDetails>(companyDtoDetails, HttpStatus.OK);

    }
}
