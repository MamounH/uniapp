package com.atypon.uniapp.controllers.users.admin.students;


import com.atypon.uniapp.data.repository.*;
import com.atypon.uniapp.data.entity.Marks;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("courseId")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentsController {

    private UserDataRepo usersDataRepo;
    private MarksDataRepo marksDataRepo;

    @GetMapping(value = "/Admin/AddStudents")
    protected String showPossibleStudents(@RequestParam int id, ModelMap modelMap) {
        modelMap.put("courseId", id);

        modelMap.addAttribute("list",usersDataRepo.getPossibleStudents(id));
        return "/Admin/AddStudents";
    }

    @PostMapping(value = "/Admin/AddStudents")
    protected String addNewStudents(@RequestParam String[] id , ModelMap modelMap) {

        int courseId = (int) modelMap.get("courseId");

        addStudents(id, courseId);

        return "redirect:/Admin/Courses";
    }

    private void addStudents(String[] id, int courseId) {
        for (String s : id) {
            Marks newMark = Marks.builder().courseId(courseId).studentId(Integer.parseInt(s)).mark("UnConfirmed").build();
            marksDataRepo.save(newMark);
        }
    }


    @GetMapping(value = "/Admin/RemoveStudents")
    protected String showEnrolledStudents(@RequestParam int id, ModelMap modelMap) {

        modelMap.put("courseId",id);
        modelMap.addAttribute("list", marksDataRepo.getEnrolledStudents(id));
        return "/Admin/RemoveStudents";
    }

    @PostMapping(value = "/Admin/RemoveStudents")
    protected String deleteStudents(@RequestParam int[] id) {

        deleteFromCourse(id);
        return "redirect:/Admin/Courses";
    }

    private void deleteFromCourse(int[] id) {
        for (int j : id) {
            marksDataRepo.deleteByGradeId(j);
        }
    }


}
