package com.forum.interceptors;

import com.forum.areas.log.models.service.LogServiceModel;
import com.forum.areas.log.services.LoggerService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private final LoggerService loggerService;

    public LoggerInterceptor(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LogServiceModel logServiceModel = new LogServiceModel();

        HandlerMethod hm;
        try {
            hm = (HandlerMethod) handler;
        } catch (ClassCastException e) {
            return;
        }
        Method method = hm.getMethod();
        if (method.getDeclaringClass().isAnnotationPresent(Controller.class)) {
            Class<?> declaringClass = method.getDeclaringClass();
            logServiceModel.setTableName(declaringClass.getSimpleName().replace("Controller", ""));
        }

        logServiceModel.setUser(request.getUserPrincipal().getName());

        if (request.getRequestURI().contains("create")) {
            logServiceModel.setOperation("create");
        } else if (request.getRequestURI().contains("edit")) {
            logServiceModel.setOperation("edit");
        } else if (request.getRequestURI().contains("delete")) {
            logServiceModel.setOperation("delete");
        }

        logServiceModel.setModifyingDate(LocalDateTime.now());

        this.loggerService.create(logServiceModel);
    }
}