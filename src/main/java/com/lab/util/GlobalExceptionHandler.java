package com.lab.util;

import com.lab.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResultVO<?> handle(Exception e) {
        return ResultVO.fail(e.getMessage());
    }
}
