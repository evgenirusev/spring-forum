package com.forum.controllers;

import com.forum.common.annotations.PreAuthenticate;
import com.forum.dtos.users.UserDto;
import com.forum.dtos.users.UserLoginDto;
import com.forum.dtos.users.UserRegisterDto;
import com.forum.entities.enums.UserRole;
import com.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public ModelAndView user(@PathVariable("username") String username, ModelAndView modelAndView) {
        modelAndView.addObject("username", username);
        return super.view("views/users/profile", "User " + username, modelAndView);
    }

    @GetMapping("/register")
    @PreAuthenticate
    public ModelAndView register() {
        return super.view("views/users/register", "Register");
    }

    @PostMapping("/register")
    @PreAuthenticate
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterDto userRegisterDto, ModelAndView modelAndView) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            // TODO: implement appropriate validation message
            return super.redirect("/register");
        }

        this.userService.createUser(userRegisterDto);

        return super.redirect("/login");
    }

    @GetMapping("/login")
    @PreAuthenticate
    public ModelAndView login() {
        return super.view("views/users/login", "Login");
    }


    @PostMapping("/login")
    @PreAuthenticate
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
    @PreAuthenticate(loggedIn = true)
    public ModelAndView logout(HttpSession session) {
        session.invalidate();

        return this.redirect("/");
    }
}
