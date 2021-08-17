package com.atypon.uniapp.data.entity;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "* Please provide user first name")
    private String first_name;

    @Column(name = "last_name")
    @NotEmpty(message = "* Please provide user last name")
    private String last_name;

    @Column(name = "email",nullable = false,unique = true)
    @Email(message = "* Please provide a valid Email")
    @NotEmpty(message = "* Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 8, message = "* User password must have at least 8 characters")
    @NotEmpty(message = "* Please provide user password")
    private String password;


    @Column(name = "role")
    private String role;
}
