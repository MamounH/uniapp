package com.atypon.uniapp.data.repository;

import com.atypon.uniapp.data.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseDataRepo extends JpaRepository<Course,Integer> {

    List<Course> findByInstructorId(int id);

    Course findById(int id);


}
