package com.example.demo.dao;

import com.example.demo.connection.DatabaseConnection;
import com.example.demo.models.PassportData;
import com.example.demo.models.RegisterData;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PassportDataDAO {
    @Autowired
    private DatabaseConnection databaseConnection;

    private List<PassportData> getPassportData() {
        JdbcTemplate jdbcTemplate = databaseConnection.getJdbcTemplate();

        return jdbcTemplate.query(
                "select * from RESIDENT.TTHOCHIEU",
                (resultSet, rowNum) -> {
                    PassportData passportData = new PassportData();
                    passportData.setAddress(resultSet.getString("DIACHI"));
                    passportData.setEmail(resultSet.getString("Email"));
                    passportData.setGender(resultSet.getString("GIOITINH"));
                    passportData.setId(resultSet.getString("CCCD"));
                    passportData.setPhone(resultSet.getString("DIENTHOAI"));
                    passportData.setStatus(resultSet.getString("TINHTRANG"));
                    passportData.setName(resultSet.getString("HOTEN"));
                    return passportData;
                }
        );
    }
}
