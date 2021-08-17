package com.atypon.uniapp.data.repository;

import com.atypon.uniapp.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface UserDataRepo extends JpaRepository<User,Integer> {

    User findById(int id);
    User findByEmail(String email);

    Boolean existsByEmail(String email);

    List<User> findByRole(String role);

    @Query(value ="SELECT * FROM student_marks m JOIN users u ON u.id=m.student_id " +
            " WHERE u.id NOT IN ( SELECT student_id from student_marks where course_id=?1) ",nativeQuery=true)
    Set<User> getPossibleStudents(int id);


    @Transactional
    @Modifying
    @Query("UPDATE User "+"SET first_name=?1, last_name=?2, email=?3 "+
            "WHERE id=?4")
    void update(String fName, String lName,String email,int id);


}