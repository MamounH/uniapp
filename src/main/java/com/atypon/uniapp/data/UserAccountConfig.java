package com.atypon.uniapp.data;

import com.atypon.uniapp.data.entity.User;
import com.atypon.uniapp.data.repository.UserDataRepo;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;

@Repository
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserAccountConfig {


    private UserDataRepo userRepo;
    private PasswordEncoder passwordEncoder;


    @Transactional
    @Modifying
    public String addUser(User user, BindingResult result){

        if (result.hasErrors()){
            return "/Admin/AddUser";
        } else {
            return saveUser(user);
        }
    }


    @Transactional
    @Modifying
    public String updateUser(User user, BindingResult result){
        if (result.hasErrors()){
            return "/Admin/UpdateUser";
        } else {
            return saveUser(user);
        }
    }

    @NotNull
    private String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/Admin/Users";
    }

}





