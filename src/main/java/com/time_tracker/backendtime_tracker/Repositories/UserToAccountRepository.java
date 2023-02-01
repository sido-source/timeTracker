package com.time_tracker.backendtime_tracker.Repositories;

import com.time_tracker.backendtime_tracker.Entities.UserToAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserToAccountRepository extends JpaRepository <UserToAccount, Long> {
    Optional<UserToAccount> findByUserId(Long id);
}
