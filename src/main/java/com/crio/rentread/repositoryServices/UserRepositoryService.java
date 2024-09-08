package com.crio.rentread.repositoryServices;

import com.crio.rentread.dto.User;
import com.crio.rentread.exceptions.UserNotFoundException;

public interface UserRepositoryService {
    
    User registerUser(String firstName, String lastName, String email, String password, String role);

    User saveUser(User user);

    User findUserById(int userId) throws UserNotFoundException;

    User findUserByEmail(String email) throws UserNotFoundException;
    
}
