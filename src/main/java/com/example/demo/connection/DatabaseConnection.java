package com.example.demo.connection;

import javax.sql.DataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class DatabaseConnection {
    private DataSource dataSource = null;
    private JdbcTemplate jdbcTemplate = null;
}
