package com.atypon.uniapp.controllers.users.instructor;


import com.atypon.uniapp.data.repository.CourseDataRepo;
import com.atypon.uniapp.data.repository.MarksDataRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({ "courseId" , "id" })
@RequestMapping("Instructor")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InstructorController {

    private CourseDataRepo courseDataRepo;

    private MarksDataRepo marksDataRepo;



    @GetMapping(value = "InstructorCourses")
    protected String showCourses(ModelMap modelMap) {
        int id = (int) modelMap.get("id");
        modelMap.addAttribute("list",courseDataRepo.findByInstructorId(id));
        return "/Instructor/InstructorCourses";

    }


    @GetMapping(value = "EnrolledStudents")
    protected String showEnrolledStudents(ModelMap modelMap, @RequestParam int courseId )  {
        modelMap.put("courseId",courseId);
        modelMap.addAttribute("list", marksDataRepo.getEnrolledStudents(courseId));
        return "/Instructor/EnrolledStudents";
    }


    @PostMapping(value = "EnrolledStudents")
    protected String submitNewMarks(@RequestParam("studentId") int[] studentId,
                        @RequestParam("mark") String[] marks, ModelMap modelMap ) {

        int courseId = (int) modelMap.get("courseId");

        updateMarks(studentId, marks, courseId);
        return "redirect:/Instructor/EnrolledStudents?courseId="+courseId;

    }

    private void updateMarks(int[] studentId, String[] marks, int courseId) {

        for (int i = 0; i< studentId.length; i++){

            int id = studentId[i];
            String mark = marks[i];
            if (!mark.isBlank()){
                marksDataRepo.updateMark(mark,id,courseId);
            }
        }

    }

}
