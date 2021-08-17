package com.atypon.uniapp.data.repository;

import com.atypon.uniapp.data.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseDataRepo extends JpaRepository<Course,Integer> {

    List<Course> findByInstructorId(int id);

    Course findById(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Course "+"SET name=?1, instructorId=?2 "+
            "WHERE id=?3")
    void update(String name, int instructor_id,int id);


}
