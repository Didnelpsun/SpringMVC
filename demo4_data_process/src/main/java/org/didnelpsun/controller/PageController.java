package org.didnelpsun.controller;

import org.didnelpsun.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

@Controller
public class PageController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
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
    @RequestMapping(value = "/responseBody", produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    // 加上这个注解后返回值不再是视图名称，而是响应的响应体
    @ResponseBody
    public String responseBody() {
        return "ResponseBody响应";
    }
    @RequestMapping(value = "/responseBodyUser", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public User responseBodyUser() {
        return new User("金","男","2000-04-12","湖北省武汉市");
    }
    @PostMapping(value = "/ajaxResponse")
    @ResponseBody
    public String ajaxResponse(String name, String password){
        return "name:" + name + " password:" + password;
    }
    @RequestMapping("/download/{filename}")
    // ResponseEntity类型为响应报文，其中响应报文内的数据类型为字节流类型，参数为Session会话对象
    public ResponseEntity<byte[]> download(HttpSession session, @PathVariable("filename") String filename) throws IOException{
        // 获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        // 获取服务器中文件的真实路径
        // servletContext.getRealPath获取当前服务器的部署路径
        // 使用File.separator取代单纯的/分隔符就能解决不同操作问题的兼容问题
        String path = servletContext.getRealPath(File.separator + "static" + File.separator + "img" + File.separator  + filename);
//        System.out.println(path);
        // 判断文件是否存在
        if(!new File(path).exists()){
            System.out.println("文件不存在！");
            // 返回状态码
            return new ResponseEntity<>(null,null,HttpStatus.NO_CONTENT);
        }
        // 创建输入流
        InputStream inputStream = new FileInputStream(path);
        // 创建字节数组
        // available()方法可以在读写操作前先得知数据流里有多少个字节可以读取
        // 在进行网络操作时往往出错，因为你调用available()方法时，对发发送的数据可能还没有到达，你得到的count是0，所以在数据到达前应该不断等待
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }
        byte[] bytes = new byte[inputStream.available()];
        // 将流读到字节数组中
        inputStream.read(bytes);
        // 创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        // 设置下载方式和下载文件名字
        // Content-disposition是MIME协议的扩展，指示MIME用户代理如何显示附加的文件。
        // 是以内联的形式（即网页或者页面的一部分），还是以附件的形式下载并保存到本地。
        // attachment附件方式下载文件
        // java.net.URLEncoder.encode(filename, StandardCharsets.UTF_8)对文件名进行编码，避免下载文件中文名乱码
        headers.add("Content-Disposition","attachment;filename="+java.net.URLEncoder.encode(filename, StandardCharsets.UTF_8));
        // 创建ResponseEntity对象
        // 响应体就是图片的流
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes,headers,HttpStatus.OK);
        // 关闭输入流
        inputStream.close();
        return responseEntity;
    }
    @PostMapping("/upload")
    // SpringMVC把这个文件封装到MultipartFile类中
    public String upload(MultipartFile file, HttpSession session) throws IOException {
        // 判断file是否传入为空
        if(file.getSize()==0){
            return "redirect:/";
        }
        // getName获取传输文件的参数名，即前端表单以什么参数名传输到后端的，这里是photo
        // getOriginalFileName获取传输文件的实际名字，即用户传输的这个文件的名字，用户传什么文件名这里就获取到什么
//        String fileName = file.getOriginalFilename();
        // 使用UUID，用随机UUID并去掉横线拼接上文件类型的后缀
        String fileName = UUID.randomUUID().toString().replaceAll("-","") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 获取文件路径
        String path = session.getServletContext().getRealPath(File.separator + "static" + File.separator + "img" + File.separator);
        // 判断路径是否存在
        if(!new File(path).exists()){
            // 不存在则创建目录
            if(!new File(path).mkdir()){
                // 创建文件目录失败
                return "redirect:/";
            }
        }
        // 设置上传地址
        file.transferTo(new File(path + File.separator + fileName));
        return "redirect:/";
    }
    // 数字计算异常
    @RequestMapping("/mathError")
    public String mathError(){
        System.out.println(1/0);
        return "index";
    }
}