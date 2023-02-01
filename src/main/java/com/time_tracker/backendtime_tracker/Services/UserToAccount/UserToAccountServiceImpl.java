package com.time_tracker.backendtime_tracker.Services.UserToAccount;

import com.time_tracker.backendtime_tracker.Entities.Company;
import com.time_tracker.backendtime_tracker.Entities.Contractor;
import com.time_tracker.backendtime_tracker.Entities.User;
import com.time_tracker.backendtime_tracker.Entities.UserToAccount;
import com.time_tracker.backendtime_tracker.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserToAccountServiceImpl implements UserToAccountService{

    @Autowired
    private UserToAccountRepository userToAccountRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserToAccount saveUserToAccount(UserToAccount userToAccount) throws Exception {
        Optional<User> user;
        Optional<Company> company ;
        Optional<Contractor> contractor;
        UserToAccount newUserToAccount = null;

        if(userToAccount.getUser() == null || userToAccount.getUser().getId() > 0){
            throw new Exception("Error with user validation");
        }

        if(!(userToAccountRepository.findByUserId(userToAccount.getId()).isPresent())){
            throw new Exception("Can not find the user");
        }else {
            user = userRepository.findById(userToAccount.getUser().getId());
        }

        if(userToAccount.getContractor().getId() == null && userToAccount.getCompany().getId() == null){
            throw new Exception("Company Id or Contract id can not be null");
        }


        if(userToAccount.getCompany().getId() != null ){
            company = companyRepository.findById(userToAccount.getCompany().getId());
            newUserToAccount = userToAccountRepository.save(new UserToAccount(null, user.get(), company.get(),null ));
        }
        if(userToAccount.getContractor().getId() != null){
            contractor = contractorRepository.findById(userToAccount.getContractor().getId());
            newUserToAccount = userToAccountRepository.save(new UserToAccount(null, user.get(), null, contractor.get()));
        }


        return newUserToAccount;
    }

    @Override
    public void deleteUserToAccount(Long id) throws Exception {

        try {
            userToAccountRepository.deleteById(id);
        } catch (Exception e){
            throw new Exception("Can not find the user");
        }

    }

    @Override
    public UserToAccount findUserAccount(Long userId) throws Exception {
        Optional<UserToAccount> userToAccounts = userToAccountRepository.findByUserId(userId);

        if(!(userToAccounts.isPresent())){
            throw new Exception("Can not find the user");
        }

        return userToAccounts.get();
    }
}
