package com.example.demo.controllers;

import com.example.demo.connection.DatabaseConnection;
import com.example.demo.dao.PassportDataDAO;
import com.example.demo.models.PassportData;
import com.example.demo.models.ResidentData;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PassportDataDAO passportDataDAO;

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
        List<PassportData> data = passportDataDAO.getPassportData();
        modelAndView.addObject("name", username);
        modelAndView.addObject("auth", true);
        modelAndView.addObject("passportData", data);

        return modelAndView;
    }

    @GetMapping("process/auth/details")
    public ModelAndView getProcessAuthDetails(@SessionAttribute(required = false) String username, @RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView("index");
        PassportData passportData = passportDataDAO.getSpecificPassportData(id);
        modelAndView.addObject("rid", id);
        modelAndView.addObject("name", username);
        modelAndView.addObject("auth", true);
        modelAndView.addObject("authDetails", true);
        modelAndView.addObject("passport", passportData);
        modelAndView.addObject("passportSection", true);

        return modelAndView;
    }

    @GetMapping("process/auth/resident")
    public ModelAndView getProcessResidentDetails(@SessionAttribute(required = false) String username, @RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView("index");
        ResidentData residentData = passportDataDAO.getSpecificResidentData(id);
        modelAndView.addObject("rid", id);
        modelAndView.addObject("name", username);
        modelAndView.addObject("auth", true);
        modelAndView.addObject("authDetails", true);
        modelAndView.addObject("resident", residentData);
        modelAndView.addObject("residentSection", true);

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
