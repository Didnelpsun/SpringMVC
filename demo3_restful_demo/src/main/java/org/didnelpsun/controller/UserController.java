// UserController.java
package org.didnelpsun.controller;

import org.didnelpsun.dao.UserDAO;
import org.didnelpsun.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    // 根据用户ID查询用户信息
    @GetMapping(value = "/user/{id}")
    public String selectUserByID(Model model, @PathVariable String id){
        model.addAttribute("user","查询用户ID"+id+"信息");
        return "user";
    }
    // 跳转到添加模式的用户信息页面
    @GetMapping(value = "/userSave")
    public String forwardInsert(Model model){
        // 设置添加模式为POST
        model.addAttribute("type","post");
        return "userSave";
    }
    // 添加用户信息
    @PostMapping(value = "/user")
    // 直接通过实体类获取数据
    public String insertUser(User user){
        // 添加数据
        userDAO.saveUser(user);
        // 返回主页面
        return "redirect:/user";
    }
    // 跳转到更新模式的用户信息页面
    @GetMapping(value = "/userSave/{id}")
    public String forwardUpdate(Model model, @PathVariable Integer id){
        model.addAttribute("type","put");
        model.addAttribute("user",userDAO.selectUser(id));
        // 由于需要保存查询的request值，所以使用转发而不是重定向
        return "userSave";
    }
    // 更新用户信息
    @PutMapping(value = "/user")
    // 直接通过实体类获取数据
    public String updateUser(User user){
        // 添加数据
        userDAO.saveUser(user);
        // 返回主页面
        return "redirect:/user";
    }
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
