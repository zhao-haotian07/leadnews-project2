package com.haotian.common.excption;


import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.common.enums.AppHttpCodeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获器
 */
@ControllerAdvice//控制器增强
@Log4j2
public class ExceptionCatch {

    //异常处理器 与上面注解一起使用，可以拦截指定的异常信息
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception exception) {
        exception.printStackTrace();
        //记录日志
        log.error("catch exception:{}", exception.getMessage());
        //返回通用异常
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }
}