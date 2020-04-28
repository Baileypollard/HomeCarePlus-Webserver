package com.bailey.projects.homecareplus.service;

import com.bailey.projects.homecareplus.repository.RoleRepository;
import com.bailey.projects.homecareplus.repository.UsersRepository;
import com.bailey.projects.homecareplus.model.CustomUserDetails;
import com.bailey.projects.homecareplus.model.Role;
import com.bailey.projects.homecareplus.model.Users;
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
