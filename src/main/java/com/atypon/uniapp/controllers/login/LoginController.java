package com.atypon.uniapp.controllers.login;

import com.atypon.uniapp.security.AuthenticationFacade;
import com.atypon.uniapp.data.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;


@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

	private AuthenticationFacade authFacade;

	@GetMapping({"", "/"})
	public String doGet() {
		Authentication authentication = authFacade.getAuthentication();

		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

		if (!authentication.isAuthenticated() || roles.contains("ROLE_ANONYMOUS")) {
			return "redirect:login?error=true";
		}
		return directUserToHomePage();

	}

	public String directUserToHomePage(){

		Role userRole = Role.valueOf(authFacade.getPrincipalUser().getRole());

		switch (userRole){
			case ADMIN -> {return "redirect:/Admin/Users";}
			case INSTRUCTOR -> {return "redirect:/Instructor/InstructorCourses";}
			case STUDENT -> {return "redirect:/Student/StudentCourses";}
			default -> {return "redirect:/login";}
		}
	}

	@GetMapping(value = "/login")
	protected String getLogin()  {
		return "login";
	}








}
