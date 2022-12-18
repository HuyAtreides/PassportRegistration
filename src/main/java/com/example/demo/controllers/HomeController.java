package com.example.demo.controllers;

import com.example.demo.connection.DatabaseConnection;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ModelAndView getHomeView(@SessionAttribute(required = false) String username) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", username);
        modelAndView.addObject("register", true);

        return modelAndView;
    }

    @GetMapping("process/register")
    public ModelAndView getProcessRegister(@SessionAttribute(required = false) String username) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", username);
        modelAndView.addObject("register", true);


        return modelAndView;
    }

    @GetMapping("process/auth")
    public ModelAndView getProcessAuth(@SessionAttribute(required = false) String username) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", username);
        modelAndView.addObject("auth", true);


        return modelAndView;
    }

    @GetMapping("process/author")
    public ModelAndView getProcessAuthor(@SessionAttribute(required = false) String username) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", username);
        modelAndView.addObject("author", true);

        return modelAndView;
    }
}
