package com.atypon.uniapp.data.repository;

import com.atypon.uniapp.data.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MarksDataRepo extends JpaRepository<Marks,Integer> {


    @Transactional
    @Modifying
    @Query("delete from Marks where gradeId=?1")
    void deleteByGradeId(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Marks "+"SET mark=?1 where studentId=?2 and courseId=?3 ")
    void updateMark(String mark, int studentId, int courseId);


    @Query(value = "SELECT * FROM student_marks AS m JOIN courses AS c  ON m.course_id = c.id" +
            " JOIN users u ON u.id = c.instructor_id WHERE m.student_id=?1",nativeQuery = true)
    List<Marks> getStudentsMarks(int id);

    @Query(value = "SELECT *" +
            " FROM `student_marks` AS u LEFT JOIN `users` AS s ON u.student_id = s.id" +
            " JOIN courses c ON c.id=u.course_id WHERE u.course_id=?1",
            nativeQuery = true)
    List<Marks> getEnrolledStudents(int id);



}
