package org.didnelpsun.controller;

import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class PageController {
    @PostMapping("/requestBody")
    public String requestBody(@RequestBody String requestBody){
        System.out.println("请求体："+requestBody);
        // 重定向
        return "redirect:/";
    }
    @PostMapping("/requestEntity")
    public String requestEntity(RequestEntity<String> requestEntity){
        System.out.println("请求头：" + requestEntity.getHeaders());
        System.out.println("请求体：" + requestEntity.getBody());
        return "redirect:/";
    }
    // 由于不需要返回视图名称，所以返回值为null
    @RequestMapping("/response/{type}")
    public void response(HttpServletResponse response, @PathVariable("type") Boolean type) throws IOException {
        if(type){
            // 字符流方式
            // 这句话的意思，是让浏览器用utf8来解析返回的数据，即设置客户端解析的编码
            response.setContentType("text/html; chartset=UTF-8");
            //这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859，即服务端对中文的编码
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("ServletAPI字符流方式响应");
        }
        else{
            // 字节流方式
            // 这句话的意思，是让浏览器用utf8来解析返回的数据,即设置客户端解析的编码
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            // response.setContentType("text/html; chartset=UTF-8");  //尝试使用这个设置“Content-type”未成功
            // 这句话的意思，使得放入流的数据是utf8格式
            response.getOutputStream().write("ServletAPI字节流方式响应".getBytes(StandardCharsets.UTF_8));
        }
    }
    @RequestMapping("/responseBody")
    public void responseBody(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("响应");
    }
}