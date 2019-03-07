package com.techprimers.security.securitydbexample.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role
{
    @Id
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role", unique = true)
    private String role;

    public Role()
    {

    }

    public Role(int id, String role)
    {
        this.roleId = id;
        this.role = role;
    }

    public int getRoleId()
    {
        return roleId;
    }

    public void setRoleId(int roleId)
    {
        this.roleId = roleId;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
