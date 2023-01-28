package com.time_tracker.backendtime_tracker.Repositories;

import com.time_tracker.backendtime_tracker.Entities.Role;
import com.time_tracker.backendtime_tracker.Models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
