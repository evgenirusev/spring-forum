package com.forum.controllers;

import com.forum.dtos.users.UserDto;
import com.forum.dtos.users.UserLoginDto;
import com.forum.dtos.users.UserRegisterDto;
import com.forum.entities.enums.UserRole;
import com.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return super.view("views/users/register", "Register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterDto userRegisterDto, ModelAndView modelAndView) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            // TODO: implement appropriate validation message
            return super.redirect("/register");
        }

        this.userService.createUser(userRegisterDto);

        return super.redirect("/login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return super.view("views/users/login", "Login");
    }


    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute UserLoginDto userLoginDto, HttpSession httpSession) {
        UserDto userDto = this.userService.getUserByUsername(userLoginDto.getUsername());

        if (userDto == null || !userLoginDto.getPassword().equals(userDto.getPassword())) {
            // TODO: implement appropriate validation message
            return super.redirect("/login");
        }

        httpSession.setAttribute("userId", userDto.getId());
        httpSession.setAttribute("username", userDto.getUsername());
        httpSession.setAttribute("userRole", userDto.getUserRole());

        return super.redirect("/home");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        
        return this.redirect("/");
    }
}
