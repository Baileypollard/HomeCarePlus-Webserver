package com.techprimers.security.securitydbexample.repository;

import com.techprimers.security.securitydbexample.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer>
{
    List<Users> findAll();

    Optional<Users> findUsersByUsername(String username);
}
