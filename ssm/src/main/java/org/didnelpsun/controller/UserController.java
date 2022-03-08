// UserController.java
package org.didnelpsun.controller;

import org.didnelpsun.entity.User;
import org.didnelpsun.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("userController")
@RequestMapping("/user")
public class UserController {
    UserServiceImpl userService;
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    @RequestMapping("/selectAllUsers")
    public String selectAllUsers(){
        List<User> users = userService.selectAllUsers();
        for(User user : users){
            System.out.println(user.toString());
        }
        return "index";
    }
}