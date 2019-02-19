package com.techprimers.security.securitydbexample.service;

import com.techprimers.security.securitydbexample.model.CustomUserDetails;
import com.techprimers.security.securitydbexample.model.Users;
import com.techprimers.security.securitydbexample.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UsersRepository usersRepository;

    public void saveUser(Users user)
    {
        usersRepository.save(user);
    }

    public void deleteUserById(String id)
    {
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
