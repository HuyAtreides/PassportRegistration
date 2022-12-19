package com.example.demo.dao;

import com.example.demo.connection.DatabaseConnection;
import com.example.demo.models.PassportData;
import com.example.demo.models.RegisterData;
import com.example.demo.models.ResidentData;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PassportDataDAO {
    @Autowired
    private DatabaseConnection databaseConnection;

    private final RowMapper<PassportData> passportDataRowMapper =  (resultSet, rowNum) -> {
        PassportData passportData = new PassportData();
        passportData.setAddress(resultSet.getString("DIACHI"));
        passportData.setEmail(resultSet.getString("Email"));
        passportData.setGender(resultSet.getString("GIOITINH"));
        passportData.setId(resultSet.getString("CCCD"));
        passportData.setPhone(resultSet.getString("DIENTHOAI"));
        passportData.setStatus(resultSet.getString("TINHTRANG"));
        passportData.setName(resultSet.getString("HOTEN"));
        return passportData;
    };

    public List<PassportData> getPassportData() {
        JdbcTemplate jdbcTemplate = databaseConnection.getJdbcTemplate();

        return jdbcTemplate.query(
                "select * from RESIDENT.TTHOCHIEU where TINHTRANG = 'Đang Xác thực'",
                passportDataRowMapper
        );
    }

    public List<PassportData> getAuthorizedPassports() {
        JdbcTemplate jdbcTemplate = databaseConnection.getJdbcTemplate();

        return jdbcTemplate.query(
                "select * from RESIDENT.LUUTRU",
                (resultSet, rowNum) -> {
                    PassportData passportData = new PassportData();
                    passportData.setId(resultSet.getString("CCCD"));
                    passportData.setStatus(resultSet.getString("TINHTRANG"));
                    passportData.setName(resultSet.getString("HOTEN"));
                    return passportData;
                }
        );
    }

    public List<PassportData> getAuthenticatedPassport() {
        JdbcTemplate jdbcTemplate = databaseConnection.getJdbcTemplate();

        return jdbcTemplate.query(
                "select * from RESIDENT.TTHOCHIEU where TINHTRANG != 'Đang Xác thực'",
                passportDataRowMapper
        );
    }

    public void approvePassport(String id) {
        JdbcTemplate jdbcTemplate = databaseConnection.getJdbcTemplate();

        jdbcTemplate.update("update RESIDENT.TTHOCHIEU set TINHTRANG = 'Đồng ý cấp' where CCCD = ? and TINHTRANG != 'Đang Xác thực'", id);
    }

    public void disapprovePassport(String id) {
        JdbcTemplate jdbcTemplate = databaseConnection.getJdbcTemplate();

        jdbcTemplate.update("update RESIDENT.TTHOCHIEU set TINHTRANG = 'Không đồng ý cấp' where CCCD = ? and TINHTRANG != 'Đang Xác thực'", id);
    }

    public void performAuthenticate(String id) {
        JdbcTemplate jdbcTemplate = databaseConnection.getJdbcTemplate();

        jdbcTemplate.update("update RESIDENT.TTHOCHIEU "
                + "set TINHTRANG = 'Đang xét duyệt' where CCCD = ? and TINHTRANG = 'Đang Xác thực'", id);
    }

    public PassportData getSpecificPassportData(String id) {
        try {
            JdbcTemplate jdbcTemplate = databaseConnection.getJdbcTemplate();

            return jdbcTemplate.queryForObject(
                    "select * from RESIDENT.TTHOCHIEU where CCCD = ?",
                    passportDataRowMapper,
                    id
            );
        }
        catch (DataAccessException ex) {
            System.out.print(ex);
            return null;
        }
    }

    public ResidentData getSpecificResidentData(String id) {
        try {
            JdbcTemplate jdbcTemplate = databaseConnection.getJdbcTemplate();

            return jdbcTemplate.queryForObject(
                    "select * from RESIDENT.TTCONGDAN where CMND = ?",
                    (resultSet, rowNum) -> {
                        ResidentData resident = new ResidentData();
                        resident.setAddress(resultSet.getString("DIACHI"));
                        resident.setEmail(resultSet.getString("Email"));
                        resident.setGender(resultSet.getString("GIOITINH"));
                        resident.setId(resultSet.getString("CMND"));
                        resident.setPhone(resultSet.getString("DIENTHOAI"));
                        resident.setName(resultSet.getString("HOTEN"));
                        resident.setNation(resultSet.getString("QUOCTICH"));
                        Date date = resultSet.getDate("NGAYSINH");
                        resident.setDateOfBirth(date.toString());
                        return resident;
                    },
                    id
            );
        }
        catch (DataAccessException dataAccessException) {
            System.out.println(dataAccessException);
            return null;
        }
    }
}
