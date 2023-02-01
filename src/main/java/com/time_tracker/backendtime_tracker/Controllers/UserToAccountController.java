package com.time_tracker.backendtime_tracker.Controllers;

import com.time_tracker.backendtime_tracker.Entities.UserToAccount;
import com.time_tracker.backendtime_tracker.Services.UserToAccount.UserToAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/userToAccount")
public class UserToAccountController {

    @Autowired
    UserToAccountServiceImpl userToAccountService;

    @PostMapping("/save")
    public ResponseEntity<UserToAccount> saveUserToAccount(@Valid @RequestBody UserToAccount userToAccount){
        UserToAccount newUserToAccount = null;

        try{
            newUserToAccount = userToAccountService.saveUserToAccount(userToAccount);
        }catch (Exception e){
            return new ResponseEntity<UserToAccount>(newUserToAccount, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<UserToAccount>(newUserToAccount, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserToAccount (@PathVariable Long id){

        try{
            userToAccountService.deleteUserToAccount(id);
        }catch (Exception e){
            return new ResponseEntity<String>("Error can not delete user", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @GetMapping("/getUserAccount/{id}")
    public ResponseEntity<UserToAccount> getUserAccount(@PathVariable Long id){
        UserToAccount newUserToAccount = null;

        try{
            newUserToAccount = userToAccountService.findUserAccount(id);
        }catch (Exception e){
            return new ResponseEntity<UserToAccount>(newUserToAccount, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserToAccount>(newUserToAccount, HttpStatus.OK);
    }


}
