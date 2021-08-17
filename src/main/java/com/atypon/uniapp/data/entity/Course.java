package com.atypon.uniapp.data.entity;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
@Getter @Setter
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "* Please provide a course name")
    private String name;

    @Column(name="instructor_id")
    @NotNull(message = "* Please provide Instructor Id")
    private int instructorId;

    @Formula("(select concat(u.first_name,' ', u.last_name) from users u where u.id = instructor_id)")
    private String instructorName;

}
