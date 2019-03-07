package com.techprimers.security.securitydbexample.repository;

import com.techprimers.security.securitydbexample.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
    Role getRoleByRole(String role);
}
