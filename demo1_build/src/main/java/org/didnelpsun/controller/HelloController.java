// HelloController.java
package org.didnelpsun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HelloController {
    // 访问index
    // RequestMapping表示请求映射，当访问path内的路径/时会调用index方法转发到index
    @GetMapping
    public String index(){
        // 返回视图名称，从而被视图解析器解析
        // 由于要访问的是/WEB-INF/pages/index.jsp，根据后面视图解析器的配置
        // 前缀为/WEB-INF/pages/，后缀为.jsp，所以这里返回的就是index字符串
        return "index";
    }
    // 必须携带value参数
//    @GetMapping(value = "/{value}", params = {"value"})
//    // 通过@PathVariable注解获取参数，括号内为参数名
//    public String hello(){
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
}