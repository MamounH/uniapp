package com.atypon.uniapp.controllers.error;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("role")
public class ErrorsController implements ErrorController {


        @GetMapping(value = "/error")
        public String handleError(HttpServletRequest request, ModelMap modelMap)
        {
            Integer requestAttribute = (Integer) request.getAttribute("javax.servlet.error.status_code");

            String statusCode = "Status Code : "+requestAttribute;

            modelMap.addAttribute("role",modelMap.get("role"));
            modelMap.addAttribute("statusCode",statusCode);

            return "/error/error";
        }






}