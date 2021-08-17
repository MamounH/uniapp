package com.atypon.uniapp.controllers.users.student;

import com.atypon.uniapp.data.repository.MarksDataRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("id")
@RequestMapping("Student")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

    MarksDataRepo marksDataRepo;


    @RequestMapping(value = "StudentCourses", method = RequestMethod.GET)
    protected String doGet(ModelMap modelMap)  {

        int id = (int) modelMap.get("id");
        modelMap.addAttribute("list", marksDataRepo.getStudentsMarks(id));
        return "Student/listStudentCourses";

    }



}
