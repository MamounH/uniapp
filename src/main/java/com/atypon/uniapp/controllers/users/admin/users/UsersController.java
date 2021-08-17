package com.atypon.uniapp.controllers.users.admin.users;


import com.atypon.uniapp.data.UserConfig;
import com.atypon.uniapp.data.repository.UserDataRepo;
import com.atypon.uniapp.data.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("Admin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsersController {


    private PasswordEncoder passwordEncoder;
    private UserDataRepo userDataRepo;
    private UserConfig userService;

    @GetMapping(value = "Users")
    protected String showAllUsers(ModelMap modelMap)  {
        modelMap.addAttribute("list",userDataRepo.findAll());
        return "/Admin/Users";
    }


    @GetMapping(value = "AddUser")
    protected String showAddUserForm(ModelMap modelMap)  {
        modelMap.addAttribute("user", User.builder().build());
        return "/Admin/AddUser";
    }


    @PostMapping(value = "AddUser")
    protected String addNewUser(@ModelAttribute @Valid User user, BindingResult result) {

        if (userDataRepo.existsByEmail(user.getEmail())){
            result.rejectValue("email", "error.user", "An account already exists for this email.");
            return "/Admin/AddUser";
        }else{
            return userService.addUser(user,result);
        }
    }

    @GetMapping(value = "UpdateUser")
    protected String showUpdateUserForm(@RequestParam int id, ModelMap modelMap) {
        modelMap.addAttribute("user",userDataRepo.findById(id));
        return "/Admin/UpdateUser";

    }

    ///edit here
    @PostMapping(value = "UpdateUser")
    protected String updateUser(@ModelAttribute @Valid User user, BindingResult result) {

            if (userDataRepo.existsByEmail(user.getEmail())) {
                result.rejectValue("email", "error.user", "An account already exists for this email.");
                return "/Admin/UpdateUser";
            } else {
                return userService.updateUser(user,result);
            }

    }

    @GetMapping(value = "DeleteUser")
    protected String deleteUser(@RequestParam int id) {
        userDataRepo.deleteById(id);
        return "redirect:/Admin/Users";
    }


}
