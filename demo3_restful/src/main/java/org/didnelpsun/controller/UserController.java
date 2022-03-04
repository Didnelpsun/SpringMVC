// UserController.java
package org.didnelpsun.controller;

import org.didnelpsun.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class UserController {
    // /user GET 查询所有用户信息
    @GetMapping(value = "/user")
    public String selectAllUsers(Model model){
        model.addAttribute("userType","查询所有用户信息");
        return "user";
    }
    // /user/id GET 根据用户ID查询用户信息
    @GetMapping(value = "/user/{id}")
    public String selectUserById(Model model, @PathVariable String id){
        System.out.println("查询用户id："+id);
        model.addAttribute("userType","查询用户ID"+id+"信息");
        return "user";
    }
    // /user POST 添加用户信息
    @PostMapping(value = "/user")
    public String insertUser(Model model, User user){
        System.out.println("添加用户："+user);
        model.addAttribute("userType","添加用户信息："+user);
        return "user";
    }
    // /user/1 DELETE 删除用户信息
    // /user PUT 修改用户信息
    @PutMapping(value = "/user")
    public String updateUser(Model model, User user){
        System.out.println("修改用户："+user);
        model.addAttribute("userType","修改用户信息："+user);
        return "user";
    }
}
