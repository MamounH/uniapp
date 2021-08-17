package com.atypon.uniapp.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atypon.uniapp.data.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

@Component
@SessionAttributes("id")
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication ) throws IOException {

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        request.getSession().setAttribute("id",userDetails.getId());
        redirectUser(response, userDetails);
    }

    private void redirectUser(HttpServletResponse response, UserPrincipal userDetails) throws IOException {
        String redirectURL;
        Role userRole = Role.valueOf(userDetails.getRole());

        switch (userRole){
            case ADMIN -> redirectURL = "/Admin/Users";
            case INSTRUCTOR -> redirectURL = "/Instructor/InstructorCourses";
            case STUDENT -> redirectURL = "/Student/StudentCourses";
            default -> redirectURL = "/login";

        }
        response.sendRedirect(redirectURL);
    }


}
