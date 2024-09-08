package com.crio.rentread.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.crio.rentread.dto.User;
import com.crio.rentread.dto.UserDetailsDto;
import com.crio.rentread.exceptions.UserNotFoundException;
import com.crio.rentread.repositoryServices.UserRepositoryService;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepositoryService userRepositoryService;

    public UserDetailsServiceImpl(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepositoryService.findUserByEmail(username);
            return new UserDetailsDto(user);

        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("User not found with this email: " + username);
        }
    }
    
}
