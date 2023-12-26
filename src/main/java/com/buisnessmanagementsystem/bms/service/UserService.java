package com.buisnessmanagementsystem.bms.service;

import com.buisnessmanagementsystem.bms.dbConnector.ManageConnection;
import com.buisnessmanagementsystem.bms.models.Users;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;

@Service
public class UserService {

    public Optional<Users> getUser(Integer id) throws SQLException {
        Optional<Users> optional = Optional.empty();
        String loginquerry = "Select * from hix_bms_data.bms_login_data where USER_ID = 1";
        Connection connection = ManageConnection.getNewConnection();
        assert connection != null;
        PreparedStatement statement = connection.prepareStatement(loginquerry);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Users user = new Users(
                    1, resultSet.getString("MAIL_ID"), 25, null
            );
            optional = Optional.of(user);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return optional;
    }
}