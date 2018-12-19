package com.forum.abstractions.controller;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    private static final String APPLICATION_TITLE = "Spring Answers";
    private static final String BASE_PAGE_LAYOUT = "layout";
    private static final String PROPERTY_VIEW_NAME = "viewName";
    private static final String PROPERTY_VIEW_MODEL = "viewModel";
    private static final String PROPERTY_TITLE = "title";
    private static final String REDIRECT_KEYWORD = "redirect:";

    protected final ModelAndView view(final String viewName, final Object viewModel, String title) {

        title = title == null ? APPLICATION_TITLE : title;

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(BASE_PAGE_LAYOUT);
        modelAndView.addObject(PROPERTY_VIEW_NAME, viewName);
        modelAndView.addObject(PROPERTY_VIEW_MODEL, viewModel);
        modelAndView.addObject(PROPERTY_TITLE, title);

        return modelAndView;
    }

    protected final ModelAndView view(final String viewName, final String title) {
        return this.view(viewName, null, title);
    }

    protected final ModelAndView view(final String viewName, final Object viewModel) {
        return this.view(viewName, viewModel, null);
    }

    protected final ModelAndView view(final String viewName) {
        return this.view(viewName, null, null);
    }

    protected final ModelAndView redirect(final String redirectUrl, final Object viewModel) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(PROPERTY_VIEW_MODEL, viewModel);
        modelAndView.setViewName(REDIRECT_KEYWORD + redirectUrl);
        return modelAndView;
    }

    protected final ModelAndView redirect(final String redirectUrl) {
        return this.redirect(redirectUrl, null);
    }
}
