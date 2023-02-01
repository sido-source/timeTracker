package com.time_tracker.backendtime_tracker.Services.UserToAccount;

import com.time_tracker.backendtime_tracker.Entities.UserToAccount;

public interface UserToAccountService {
    public UserToAccount saveUserToAccount(UserToAccount userToAccount) throws Exception;
    public void deleteUserToAccount(Long id) throws Exception;

    public UserToAccount findUserAccount(Long userId) throws Exception;
}
