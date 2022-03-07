// ExceptionController.java
package org.didnelpsun.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 当前类为异常处理类
@ControllerAdvice
public class ExceptionController {
    // 标识处理数字计算异常
    @ExceptionHandler(ArithmeticException.class)
    // exception为当前异常对象
    public String arithmeticException(Exception exception, Model model){
        // 向request域添加参数
        model.addAttribute("exception",exception);
        return "error";
    }
}
