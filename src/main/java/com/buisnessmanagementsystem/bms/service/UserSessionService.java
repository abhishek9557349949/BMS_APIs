package com.buisnessmanagementsystem.bms.service;

import com.buisnessmanagementsystem.bms.constants.BMSConstants;
import com.buisnessmanagementsystem.bms.dao.UserSessionDao;
import com.buisnessmanagementsystem.bms.exceptions.ServiceExceptions;
import com.buisnessmanagementsystem.bms.models.LoginData;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserSessionService {
    public static LoginData getLoginDetails(LoginData loginDetails) throws Exception {
        try {
            LoginData loginResponseDetails = null;
            if (loginDetails.getUserId() != null && !loginDetails.getUserId().isBlank()
                    && loginDetails.getPassword() != null && !loginDetails.getPassword().isBlank()) {
                if (notValidLogin(loginDetails)) {
                    loginDetails.setResponse(BMSConstants.FAILED_LOGIN_RESPONSE);
                    return loginDetails;
                }
                loginResponseDetails = UserSessionDao.getUserDetails(loginDetails);
            } else {
                loginDetails.setResponse(BMSConstants.FAILED_LOGIN_RESPONSE);
            }
            return loginResponseDetails;
        }catch(Exception e){
            throw new ServiceExceptions(BMSConstants.LOGIN_SERVICE_EXCEPTION);
        }
    }

    public static LoginData signupRequest(LoginData loginDetails) throws Exception {
        LoginData signupResponse;
        try{
            if(!checkValidUserId(loginDetails.getUserId())){
                signupResponse = new LoginData(
                        null, null, 0, null, null, null, null,
                        null, null, "User already exists");
            }else {
                signupResponse = UserSessionDao.signupPerson(loginDetails);
            }
        }catch(Exception e){
            throw new ServiceExceptions(BMSConstants.LOGIN_SERVICE_EXCEPTION);
        }
        return signupResponse;
    }

    private static boolean checkValidUserId(String userId) throws SQLException {
        if(userId.length() < 3){
            return false;
        }
        if(UserSessionDao.CheckValidLogin(userId)){
            return true;
        };
        return false;
    }

    private static boolean notValidLogin(LoginData loginDetails) {
        if(loginDetails.getUserId().length() <= 3 || loginDetails.getPassword().length() <= 3){
            return true;
        }
        return false;
    }
}