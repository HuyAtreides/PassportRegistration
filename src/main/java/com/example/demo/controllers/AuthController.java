package com.example.demo.controllers;

import com.example.demo.connection.DatabaseConnection;
import com.example.demo.models.RegisterData;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private DatabaseConnection databaseConnection;

    @PostMapping("login")
    public String login(@RequestParam String username, @RequestParam String password,
            HttpSession session) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                "jdbc:oracle:thin:@192.168.1.10:1521/orcl.localdomain");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        databaseConnection.setDataSource(dataSource);
        databaseConnection.setJdbcTemplate(new JdbcTemplate(dataSource));
        session.setAttribute("username", username);

        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        databaseConnection.setDataSource(null);
        session.setAttribute("username", null);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(RegisterData registerData) {

        JdbcTemplate template = databaseConnection.getJdbcTemplate();
        template.update("""
                        insert into resident.tthochieu (cccd, gioitinh, diachi, dienthoai, email, hoten, tinhtrang)
                        values (?, ?, ?, ?, ?, ?, 'Đang Xác thực')
                        """, registerData.getId(), registerData.getGender(), registerData.getAddress(),
                registerData.getPhone(), registerData.getEmail(), registerData.getName());
        return "redirect:/";


    }
}
