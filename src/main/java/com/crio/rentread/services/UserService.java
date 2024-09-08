package com.crio.rentread.services;

import com.crio.rentread.dto.User;
import com.crio.rentread.exceptions.InvalidCredentialsException;
import com.crio.rentread.exceptions.UserNotRegisteredException;
import com.crio.rentread.exchanges.LoginUserRequest;
import com.crio.rentread.exchanges.RegisterUserRequest;

public interface UserService {
    
    User registerUser(RegisterUserRequest registerUserRequest);

    String loginUser(LoginUserRequest loginUserRequest) throws InvalidCredentialsException, UserNotRegisteredException;

}
