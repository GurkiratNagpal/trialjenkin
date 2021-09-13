package com.auth.Signing.Project_Login.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "users1", uniqueConstraints ={@UniqueConstraint(columnNames = "emailId")})
public @Data class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private boolean enabled;
    @Column(nullable = false)
    private String firstname;
    //@Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String emailid;
    //@Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Provider provider;

}
