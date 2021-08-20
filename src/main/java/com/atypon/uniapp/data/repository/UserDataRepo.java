package com.atypon.uniapp.data.repository;

import com.atypon.uniapp.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface UserDataRepo extends JpaRepository<User,Integer> {

    User findById(int id);
    User findByEmail(String email);

    Boolean existsByEmail(String email);

    List<User> findByRole(String role);

    @Query(value ="select t1.* from users t1 where t1.role='STUDENT' AND not exists " +
            "(select * from student_marks t2 where t1.id = t2.student_id and course_id=?1)",
            nativeQuery=true)
    Set<User> getPossibleStudents(int id);



}