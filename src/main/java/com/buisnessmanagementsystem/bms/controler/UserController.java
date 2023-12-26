package com.buisnessmanagementsystem.bms.controler;
import com.buisnessmanagementsystem.bms.models.LoginData;
import com.buisnessmanagementsystem.bms.models.Users;
import com.buisnessmanagementsystem.bms.service.UserService;
import com.buisnessmanagementsystem.bms.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping ("/user")
    public Users getUser(@RequestBody Users id) throws SQLException {
        Optional<Users> user = userService.getUser(id.getId());
        return (Users) user.orElse(null);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping ("/login")
    public LoginData sessionLogin(@RequestBody LoginData loginData) throws Exception {
        return UserSessionService.getLoginDetails(loginData);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping ("/signup")
    public LoginData sessionSignup(@RequestBody LoginData loginData) throws Exception {
        return UserSessionService.signupRequest(loginData);
    }
}