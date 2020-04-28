package com.bailey.projects.homecareplus.repository;

import com.bailey.projects.homecareplus.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
    Role getRoleByRole(String role);
}
