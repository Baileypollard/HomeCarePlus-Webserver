package com.bailey.projects.homecareplus.repository;

import com.bailey.projects.homecareplus.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer>
{
    List<Users> findAll();

    Optional<Users> findUsersByUsername(String username);

    @Transactional
    void deleteUsersByUsername(String user);
}
