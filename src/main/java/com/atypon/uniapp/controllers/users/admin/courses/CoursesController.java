package com.atypon.uniapp.controllers.users.admin.courses;


import com.atypon.uniapp.data.Role;
import com.atypon.uniapp.data.entity.User;
import com.atypon.uniapp.data.repository.CourseDataRepo;
import com.atypon.uniapp.data.repository.UserDataRepo;
import com.atypon.uniapp.data.entity.Course;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("Admin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CoursesController {

    private CourseDataRepo courseDataRepo;
    private UserDataRepo userDataRepo;


    @GetMapping(value = "Courses")
    protected String showAllCourses(ModelMap modelMap) {
        modelMap.addAttribute("list", courseDataRepo.findAll());
        return "Admin/Courses";
    }


    @GetMapping("AddCourse")
    protected String getPossibleInstructors(ModelMap modelMap)  {
        modelMap.addAttribute("list",userDataRepo.findByRole(String.valueOf(Role.INSTRUCTOR)));
        return "/Admin/AddCourse";
    }

    @PostMapping(value = "AddCourse" )
    protected String addNewCourse(@RequestParam String name , @RequestParam int instructor_id) {
        Course course=Course.builder().name(name).instructorId(instructor_id).build();
        courseDataRepo.save(course);
        return "redirect:/Admin/Courses";
    }

    @GetMapping(value = "UpdateCourse")
    protected String showUpdateCourseForm(@RequestParam int id, ModelMap modelMap)  {

        Course course = courseDataRepo.findById(id);
        modelMap.addAttribute("course",course);
        return "/Admin/UpdateCourse";

    }

    @PostMapping(value = "UpdateCourse")
    protected String submitUpdatedCourse(@ModelAttribute @Valid Course course, BindingResult result) {

        if (result.hasErrors()){
            return "/Admin/UpdateCourse";
        } else {
            courseDataRepo.save(course);
            return "redirect:/Admin/Courses";
        }

    }

    @GetMapping(value = "DeleteCourse")
    protected String deleteCourse(@RequestParam int id)  {
        courseDataRepo.deleteById(id);
        return "redirect:/Admin/Courses";
    }

}
