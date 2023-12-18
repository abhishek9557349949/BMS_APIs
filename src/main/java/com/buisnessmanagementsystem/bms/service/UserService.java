package com.buisnessmanagementsystem.bms.service;

import com.buisnessmanagementsystem.bms.models.Users;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<Users> userList;

    public UserService() {
        userList = new ArrayList<>();

        Users user1 = new Users(1,"Ida", 32, "ida@mail.com");
        Users user2 = new Users(2,"Hans", 26, "hans@mail.com");
        Users user3 = new Users(3,"Lars", 45, "lars@mail.com");
        Users user4 = new Users(4,"Ben", 32, "ben@mail.com");
        Users user5 = new Users(5,"Eva", 59, "eva@mail.com");

        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
    }

    public Optional<Users> getUser(Integer id) {
        Optional<Users> optional = Optional.empty();
        for (Users user: userList) {
            if(id == user.getId()){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }
}