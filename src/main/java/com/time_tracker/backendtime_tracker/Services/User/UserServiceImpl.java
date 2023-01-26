package com.time_tracker.backendtime_tracker.Services.User;

import com.time_tracker.backendtime_tracker.Entities.Role;
import com.time_tracker.backendtime_tracker.Entities.User;
import com.time_tracker.backendtime_tracker.Repositories.RoleRepository;
import com.time_tracker.backendtime_tracker.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} in database", user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} in database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) throws Exception {
        log.info("Adding role {} to user {} ", roleName, username);
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Role> role = roleRepository.findByName(roleName);

        if(!(user.isPresent())){
            throw new Exception("There is no user for given username");
        }


        if(!(role.isPresent())){
            throw new Exception("There is no role for given rolename");
        }

        user.get().getRoles().add(role.get());
    }

    @Override
    public User getUser(String username) throws Exception {
        log.info("Fetching user {}", username);
        Optional<User> user = userRepository.findByUsername(username);

        if(!(user.isPresent())){
            throw new Exception("There is no user for given username");
        }

        return user.get();
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all the users");
        return userRepository.findAll();
    }
}
