package cn.endacsd.yingbookshop.controller;

import cn.endacsd.yingbookshop.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({Exception.class})
    @ResponseBody
    public R globalException(HttpServletResponse response, Exception ex){
        System.out.println(ex.getMessage());
        System.out.println(response.getStatus());
        return R.error(500,ex.getMessage());
    }

}