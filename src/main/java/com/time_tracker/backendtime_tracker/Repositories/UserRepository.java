package com.time_tracker.backendtime_tracker.Repositories;

import com.time_tracker.backendtime_tracker.Entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
