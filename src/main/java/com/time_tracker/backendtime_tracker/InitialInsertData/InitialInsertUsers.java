package com.time_tracker.backendtime_tracker.InitialInsertData;

import com.time_tracker.backendtime_tracker.Entities.Role;
import com.time_tracker.backendtime_tracker.Entities.User;
import com.time_tracker.backendtime_tracker.Services.User.UserService;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;

public class InitialInsertUsers {
    public CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_COMPANY"));
            userService.saveRole(new Role(null, "ROLE_MENAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUser(new User(null, "Jan Nowak", "jan", "pass", new ArrayList<>()));
            userService.saveUser(new User(null, "Deloitte", "deloitte", "pass", new ArrayList<>()));
            userService.saveUser(new User(null, "Tomek Zakrzewski", "tomek", "pass", new ArrayList<>()));
            userService.saveUser(new User(null, "Gal Anonim", "gal", "pass", new ArrayList<>()));

            userService.addRoleToUser("gal", "ROLE_ADMIN");
            userService.addRoleToUser("jan", "ROLE_USER");
            userService.addRoleToUser("jan", "ROLE_MENAGER");
            userService.addRoleToUser("deloitte", "ROLE_COMPANY");

        };
    }
}
