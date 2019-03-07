package com.techprimers.security.securitydbexample.service;

import com.techprimers.security.securitydbexample.model.CustomUserDetails;
import com.techprimers.security.securitydbexample.model.Role;
import com.techprimers.security.securitydbexample.model.Users;
import com.techprimers.security.securitydbexample.repository.RoleRepository;
import com.techprimers.security.securitydbexample.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UsersRepository usersRepository;


    @Autowired
    private RoleRepository repository;

    public void saveUser(Users user)
    {
        Role role = repository.getRoleByRole("ROLE_ADMIN");
        user.getRoles().add(role);
        usersRepository.save(user);
    }

    public void deleteUserByUsername(String username)
    {
        usersRepository.deleteUsersByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<Users> optionalUsers = usersRepository.findUsersByUsername(username);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers
                .map(CustomUserDetails::new).get();
    }
}
