package com.time_tracker.backendtime_tracker.Services.User;

import com.time_tracker.backendtime_tracker.Entities.Role;
import com.time_tracker.backendtime_tracker.Entities.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName) throws Exception;
    User getUser(String username) throws Exception;

    //if there is many users in databse much butter approach is to getUsers from particular page
    //and using pagination return every user from database
    //insted of returning some big number of user at once
    List<User> getUsers();
}
