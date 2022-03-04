// UserController.java
package org.didnelpsun.controller;

import org.didnelpsun.dao.UserDAO;
import org.didnelpsun.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    // 由于没有业务层，本来是Service调用DAO，所以此时直接Controller调用DAO
    private UserDAO userDAO;
    // 自动装配
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    // 查询所有用户信息
    @GetMapping(value = "/user")
    public String selectAllUsers(Model model){
        model.addAttribute("users",userDAO.selectAllUsers());
        return "user";
    }
//    // 根据用户ID查询用户信息
//    @GetMapping(value = "/user/{id}")
//    public String selectUserByID(Model model, @PathVariable String id){
//        model.addAttribute("user","查询用户ID"+id+"信息");
//        return "user";
//    }
//    // 添加用户信息
//    @PostMapping(value = "/user")
//    public String insertUser(Model model, User user){
//        System.out.println("添加用户："+user);
//        model.addAttribute("userType","添加用户信息："+user);
//        return "user";
//    }
    // 删除用户信息
    @DeleteMapping(value = "user/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userDAO.deleteUser(id);
        // 要重新回到查询所有的主页面
        // 如果使用转发，则URL仍然是user/id，这就不是我们想要的
        // 因为进行删除这个请求之后跟原来的请求就没有关系了，所以使用重定向
        return "redirect:/user";
    }
}
