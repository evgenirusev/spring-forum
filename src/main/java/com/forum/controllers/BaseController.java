package com.forum.controllers;

import org.springframework.web.servlet.ModelAndView;

abstract class BaseController {
    private static final String LAYOUT_VIEW_NAME = "layout";
    private static final String DEFAULT_TITLE_NAME = "Spring-Answers";
    private static final String DEFAULT_THYMELEAF_VIEW_NAME_KEY = "viewName";
    private static final String DEFAULT_THYMELEAF_TITLE_KEY = "title";
    private static final String DEFAULT_SPRING_REDIRECT_KW = "redirect:";

    ModelAndView view(String view){
        return this.view(view, null);
    }

    ModelAndView view(String view, String title){
        return this.view(view, title, null);
    }

    ModelAndView view(String view, String title, ModelAndView modelAndView) {

        title = title == null ? DEFAULT_TITLE_NAME : title;
        modelAndView = modelAndView == null ? new ModelAndView() : modelAndView;

        modelAndView.addObject(DEFAULT_THYMELEAF_TITLE_KEY, title);

        modelAndView.addObject(DEFAULT_THYMELEAF_VIEW_NAME_KEY, view);
        modelAndView.setViewName(LAYOUT_VIEW_NAME);

        return modelAndView;
    }

    ModelAndView redirect(String route) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(DEFAULT_SPRING_REDIRECT_KW + route);
        return modelAndView;
    }
}