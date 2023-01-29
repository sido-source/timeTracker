package com.time_tracker.backendtime_tracker.initialDataLoading;

import com.time_tracker.backendtime_tracker.Entities.Role;
import com.time_tracker.backendtime_tracker.Repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;

import static com.time_tracker.backendtime_tracker.Models.ERole.*;

public class InsertRules {
    public CommandLineRunner run(RoleRepository roleRepository){
        return args -> {
            roleRepository.save(new Role(1, ROLE_ADMIN));
            roleRepository.save(new Role(2, ROLE_COMPANY));
            roleRepository.save(new Role(3, ROLE_USER));
        };
    }
}
