package com.crio.rentread.repositoryServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crio.rentread.dto.User;
import com.crio.rentread.exceptions.UserNotFoundException;
import com.crio.rentread.mapper.Mapper;
import com.crio.rentread.models.RoleEntity;
import com.crio.rentread.models.UserEntity;
import com.crio.rentread.repositories.RoleRepository;
import com.crio.rentread.repositories.UserRepository;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registerUser(String firstName, String lastName, String email, String password, String role) {
        String encodedPassword = bCryptPasswordEncoder.encode(password);

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setEmail(email);
        userEntity.setPassword(encodedPassword);
        RoleEntity roleEntity = findOrCreateRole(role);
        userEntity.setRole(roleEntity);

        userRepository.save(userEntity);

        User user = Mapper.mapToUser(userEntity);
        
        return user;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = Mapper.mapToUserEntity(user);
        return Mapper.mapToUser(userRepository.save(userEntity));
    }

    @Override
    public User findUserById(int userId) throws UserNotFoundException {
        String message = "Could not find user with this ID: " + String.valueOf(userId);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(message));
        User user = Mapper.mapToUser(userEntity);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        String message = "Could not find user with this email: " + email;
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(message));
        User user = Mapper.mapToUser(userEntity);
        return user;
    }

    private RoleEntity findOrCreateRole(String roleName) {
		Optional<RoleEntity> maybeRoleEntity = roleRepository.findByName(roleName);

		if(maybeRoleEntity.isPresent()) {
			return maybeRoleEntity.get();
		}
		else {
			RoleEntity roleEntity = new RoleEntity();
			roleEntity.setName(roleName);
			roleRepository.save(roleEntity);
			return roleEntity;
		}

	}
    
}
