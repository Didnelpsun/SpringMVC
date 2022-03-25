// PageController.java
package org.didnelpsun.controller;

import org.didnelpsun.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
@SessionAttributes(value = {"session"}, types = {String.class})
public class PageController {
    // 访问index
    // RequestMapping表示请求映射，当访问path内的路径/时会调用index方法转发到index
//    @GetMapping
//    public String index(){
//        // 返回视图名称，从而被视图解析器解析
//        // 由于要访问的是/WEB-INF/pages/index.jsp，根据后面视图解析器的配置
//        // 前缀为/WEB-INF/pages/，后缀为.jsp，所以这里返回的就是index字符串
//        return "index";
//    }
    // 必须携带value参数
    @RequestMapping(value = "/{value}", params = {"value"}, headers = {"Host=localhost:8082"})
    // 通过@PathVariable注解获取参数，括号内为参数名
    public String hello(@PathVariable("value")String value){
        System.out.println(value);
        return "index";
    }
    // 测试ant风格路径
    @RequestMapping("**/a*")
    public String testAnt(){
        return "index";
    }
    @RequestMapping(value = "/restful/{value}")
    // 通过@PathVariable注解获取参数，括号内为参数名
    public String restful(@PathVariable("value")String value){
        System.out.println(value);
        return "index";
    }
//    @RequestMapping("/param")
//    public String paramApi(HttpServletRequest request){
//        String value = request.getParameter("value");
//        System.out.println(value);
//        return "param";
//    }
//    @RequestMapping("/param")
//    public String paramController(String value,
//                                  @RequestParam(value = "user", required = false) String name,
//                                  @RequestHeader("Host") String host,
//                                  @CookieValue("JSESSIONID") String cookie
//    ){
//        System.out.println("value:" + value + " name:" + name);
//        System.out.println("host:" + host);
//        System.out.println("cookie:" + cookie);
//        return "param";
//    }
    @ResponseBody
    @RequestMapping(value = "/{path}", produces = {"application/json;charset=utf-8"})
    public Map<String, Object> paramMatrix(@MatrixVariable("id") Integer[] id,
                                           @MatrixVariable("name") String[] name,
                                           @PathVariable String[] path){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("path", path);
        return map;
    }
//    @RequestMapping("/param")
//    public String paramPojo(User user){
//        System.out.println(user);
//        return "param";
//    }
    @RequestMapping("/share")
    public String share(){
        return "share";
    }
    // 使用servletAPI向Request域共享数据
    @RequestMapping("/share/shareServletAPI")
    public String shareServletAPI(HttpServletRequest request){
        // 向域对象共享数据
        request.setAttribute("shareType", "ServletAPI");
        return "share";
    }
    // 使用ModelAndView向Request域共享数据
    @RequestMapping("/share/shareModelAndView")
    public ModelAndView shareModelAndView(){
        ModelAndView modelAndView = new ModelAndView();
        // 处理模型数据，即向请求域request共享数据
        // addObject将对象添加到模型中，即添加到request域中，这个方法相当于request.setAttribute
        modelAndView.addObject("shareType", "ModelAndView");
        // 设置视图名称，即应该跳转到的视图页面名称，这里是share
        modelAndView.setViewName("share");
        return modelAndView;
    }
    // 使用Model向Request域共享数据
    @RequestMapping("/share/shareModel")
    public String shareModel(Model model){
        // 添加属性
        model.addAttribute("shareType", "Model");
        return "share";
    }
    // 使用Map向Request域共享数据
    @RequestMapping("/share/shareMap")
    public String shareMap(Map<String, Object> map){
        // 添加属性
        map.put("shareType", "Map");
        return "share";
    }
    // 使用ModelMap向Request域共享数据
    @RequestMapping("/share/shareModelMap")
    public String shareModelMap(ModelMap modelMap){
        // 添加属性
        modelMap.addAttribute("shareType", "ModelMap");
        return "share";
    }
    // 向Session域共享数据
    @RequestMapping("/share/shareSession")
    public String shareSession(HttpSession session){
        // 添加属性
        session.setAttribute("session", "Session");
        return "share";
    }
    // 向Application域共享数据
    @RequestMapping("/share/shareApplication")
    public String shareApplication(HttpSession session){
        ServletContext application = session.getServletContext();
        // 添加属性
        application.setAttribute("application", "Application");
        return "share";
    }
    // 重定向
    @RequestMapping("/redirect")
    public String redirect(){
        return "redirect:/";
    }
}