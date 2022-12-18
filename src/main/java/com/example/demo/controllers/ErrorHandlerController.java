package com.example.demo.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception error, HttpSession session, @SessionAttribute(required = false) String username) {
        System.out.println(error);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", username);
        modelAndView.addObject("error", true);

        return modelAndView;
    }
}
