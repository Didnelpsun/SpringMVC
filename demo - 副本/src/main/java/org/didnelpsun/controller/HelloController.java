// HelloController.java
package org.didnelpsun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping(path = "/hello")
    public String sayHello(){
        System.out.println("hello springmvc");
        // 返回值默认为JSP的名字，页面在WEB-INF下
        // 需要在SpringMVC.xml中配置视图解析器
        return "success";
    }
}
