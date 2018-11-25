package com.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends BaseController {
    @GetMapping("/register")
    public ModelAndView register() {
        return super.view("views/users/register", "Register");
    }
}
