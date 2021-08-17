package com.atypon.uniapp.data.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_marks")
public class Marks {


    @Id
    @Column(name="g_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int gradeId;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;



    @Column(name = "mark")
    private String mark;

    @Column(insertable = false)
    public String first_name;

    @Column(insertable = false)
    public String last_name;

    @Column(insertable = false)
    private String name;

    @Column(insertable = false)
    private String email;


}
