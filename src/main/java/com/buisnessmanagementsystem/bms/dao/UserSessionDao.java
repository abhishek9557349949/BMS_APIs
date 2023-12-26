package com.buisnessmanagementsystem.bms.dao;

import com.buisnessmanagementsystem.bms.constants.BMSConstants;
import com.buisnessmanagementsystem.bms.cypherhandeling.CypherTextEncryptDecrypt;
import com.buisnessmanagementsystem.bms.dbConnector.ManageConnection;
import com.buisnessmanagementsystem.bms.models.LoginData;
import com.buisnessmanagementsystem.bms.sqlStrings.SQLStrings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSessionDao {
    public static LoginData getUserDetails(LoginData loginData) throws Exception {
        String loginquerry = SQLStrings.loginString;
        Connection connection = ManageConnection.getNewConnection();
        assert connection != null;
        PreparedStatement statement = connection.prepareStatement(loginquerry);
        statement.setString(1, loginData.getUserId());
        statement.setString(2, CypherTextEncryptDecrypt.encrypt(loginData.getPassword(), CypherTextEncryptDecrypt.stringToSecretKey(BMSConstants.ENCRYPTION_KEY)));
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            LoginData loginInfo = new LoginData(
                    resultSet.getString("CLIENT_NAME"),
                    resultSet.getString("MAIL_ID"),
                    resultSet.getInt("FAILED_LOGIN_ATTEMPTS"),
                    resultSet.getString("ACCOUNT_STATUS"),
                    resultSet.getString("BUSINESS_NAME"),
                    resultSet.getString("ROLE"),
                    resultSet.getString("APPROVED_BY_OWNER"),
                    loginData.getUserId(),null,"success");

            if (!loginInfo.getAccountStatus().equalsIgnoreCase(BMSConstants.ACTIVE_ACCOUNT)
                    || loginInfo.getFailedLoginAttempts() > 3) {
                    loginInfo.setResponse(BMSConstants.FAILED_LOGIN_RESPONSE);
            }
            if(!loginInfo.getApprovedByOwner().equalsIgnoreCase(BMSConstants.YES_STRING)){
                loginInfo.setResponse(BMSConstants.FAILED_LOGIN_RESPONSE);
            }
            resultSet.close();
            statement.close();
            ManageConnection.closeConnection(connection);
            return loginInfo;
        }else{
            String incorrectLogin = SQLStrings.incorrectLogin;
            PreparedStatement logoutStatement = connection.prepareStatement(incorrectLogin);
            logoutStatement.setString(1, loginData.getUserId());
            logoutStatement.executeUpdate();
            LoginData loginInfo = new LoginData(
                    null,null,0,null, null, null,null,
                    loginData.getUserId(),null,"Incorrect Password");
            return loginInfo;
        }
    }

    public static boolean CheckValidLogin(String userId) throws SQLException {
        try {
            String checkValidUser = SQLStrings.checkValidUser;
            Connection connection = ManageConnection.getNewConnection();
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(checkValidUser);
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet);
                resultSet.close();
                statement.close();
                ManageConnection.closeConnection(connection);
                return false;
            } else {
                resultSet.close();
                statement.close();
                ManageConnection.closeConnection(connection);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static LoginData signupPerson(LoginData loginDetails) throws Exception {

        Connection connection = ManageConnection.getNewConnection();
        assert connection != null;
        String signupLogin = SQLStrings.signupLogin;
        PreparedStatement signupStatement = connection.prepareStatement(signupLogin);
        signupStatement.setString(1, CypherTextEncryptDecrypt.encrypt(loginDetails.getPassword(), CypherTextEncryptDecrypt.stringToSecretKey(BMSConstants.ENCRYPTION_KEY)));
        signupStatement.setString(2, loginDetails.getUserId());
        signupStatement.setString(3, loginDetails.getMailId());
        signupStatement.setString(4, loginDetails.getClientName());
        signupStatement.setInt(5, 0);
        signupStatement.setString(6, "Active");
        signupStatement.setString(7, loginDetails.getBusinessName());
        signupStatement.setString(8, loginDetails.getRole());
        int isupdated = signupStatement.executeUpdate();
        LoginData loginInfo;
        if (isupdated != 0) {
            loginDetails.setPassword(null);
            loginDetails.setResponse("user registered successfully");
            loginInfo = loginDetails;

        } else {
            loginInfo = new LoginData(
                    null, null, 0, null, null, null, null,
                    null, null, "there is some error please try again after some time");
        }
        signupStatement.close();
        ManageConnection.closeConnection(connection);
        return loginInfo;
    }
}
