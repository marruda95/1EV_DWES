package com.sanvalero.AAMaria_Arruda.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/** USUARIOS DE LA APLICACION **/
@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private String nif;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column
    private String address;
    @Column
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    @Column
    private String province;
    @Column
    private String country;
    @Column
    private String image;
    @Column (name = "creation_date")
    private LocalDate creationDate;
    @Column (name = "last_login")
    private LocalDateTime lastLogin;
    @Column
    private boolean active;
    @Transient
    private int age;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
