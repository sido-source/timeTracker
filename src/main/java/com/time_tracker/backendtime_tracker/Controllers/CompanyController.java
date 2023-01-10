package com.time_tracker.backendtime_tracker.Controllers;

import com.time_tracker.backendtime_tracker.Dtos.Company.CompanyDto;
import com.time_tracker.backendtime_tracker.Dtos.OutcommingResponses;
import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Services.Company.CompanyService;
import jakarta.validation.Valid;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/api/company/")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    public CompanyController(){

    }

    @PostMapping("save")
    public ResponseEntity<String> saveCompany(@RequestBody CompanyDto companyDto){
        try {
            companyService.saveCompany(companyDto);
        } catch (Exception e) {
            //throw new RuntimeException(e);

            return new ResponseEntity<String>(e.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Company has been succesfully created. Your unique indetifier is: " + companyDto.getName(), HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<Set<Company>> getAllCompanies(){
        // we return the list of all Companies, regardles of the number of returnred elements
        // in worst case, 0 element list will be returned <==> null

        Set<Company> companySet = null;

        try {
            companySet = new HashSet<Company>(companyService.getAllCompanies());
        }catch (Exception e){
            return new ResponseEntity<Set<Company>>(companySet ,HttpStatus.OK);
        }
        return new ResponseEntity<Set<Company>>(companySet, HttpStatus.OK);
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
    public ResponseEntity<CompanyDto> getSpecificCompany(@PathVariable("name") String companyName) throws Exception {

        CompanyDto companyDto = null;
        try{
            companyDto = companyService.getSpecificCompany(companyName);
        }catch(Exception e){
            return new ResponseEntity<CompanyDto>(companyDto, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<CompanyDto>(companyDto, HttpStatus.OK);

    }
}
