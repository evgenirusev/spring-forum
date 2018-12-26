package com.forum.exceptions;

import com.forum.abstractions.controller.BaseController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseController {
    private static final String DEFAULT_ERROR_MESSAGE = "There was an error with your request.";

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView getException(RuntimeException e) {
        String errorMessage = e.getClass().isAnnotationPresent(ResponseStatus.class)
                ? e.getClass().getAnnotation(ResponseStatus.class).reason()
                : DEFAULT_ERROR_MESSAGE;
        return super.view("/errors/default", errorMessage, "Something Went Wrong");
    }
}