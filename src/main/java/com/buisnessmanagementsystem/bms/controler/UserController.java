package com.buisnessmanagementsystem.bms.controler;
import com.buisnessmanagementsystem.bms.models.Users;
import com.buisnessmanagementsystem.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Users getUser(@RequestParam Integer id) {
        Optional<Users> user = userService.getUser(id);
        return (Users) user.orElse(null);
    }
}