package com.forum.controllers;

import com.forum.dtos.users.UserRegisterDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends BaseController {

    @GetMapping("/register")
    public ModelAndView register() {
        return super.view("views/users/register", "Register");
    }

    @PostMapping("register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterDto userRegisterDto) {
        // TODO: implement
        return null;
    }
}
