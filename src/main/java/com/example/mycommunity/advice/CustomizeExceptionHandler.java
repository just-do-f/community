package com.example.mycommunity.advice;

import com.example.mycommunity.exception.CustumizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Create by czl on 2021/7/9 14:52
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model){
        if(e instanceof CustumizeException){
            model.addAttribute("message",e.getMessage());
        }else {
            model.addAttribute("message","服务器冒烟了。要不然你稍后再试试！！！");
        }
        return new ModelAndView("error");
    }

}
