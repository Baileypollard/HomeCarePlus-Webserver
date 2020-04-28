package com.bailey.projects.homecareplus.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
public class Users
{
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "active")
    private int active;
    @Column(name = "phone_numb")
    private String phoneNumb;
    @Column(name = "address")
    private String address;
    @Column(name = "username")
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public Users()
    {
    }

    public Users(String email, String password, String firstName, String lastName, int active, String phoneNumb, String address, String username, Collection<Role> roles)
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.phoneNumb = phoneNumb;
        this.address = address;
        this.username = username;
        this.roles = roles;
    }

    public Users(Users users)
    {
        this.username = users.getUsername();
        this.id = users.getId();
        this.active = users.getActive();
        this.email = users.getEmail();
        this.roles = users.getRoles();
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.password = users.getPassword();
        this.phoneNumb = users.getPhoneNumb();
        this.address = users.getAddress();
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhoneNumb()
    {
        return phoneNumb;
    }

    public void setPhoneNumb(String phoneNumb)
    {
        this.phoneNumb = phoneNumb;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setName(String name)
    {
        this.firstName = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getActive()
    {
        return active;
    }

    public void setActive(int active)
    {
        this.active = active;
    }

    public Collection<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Collection<Role> roles)
    {
        this.roles = roles;
    }
}
