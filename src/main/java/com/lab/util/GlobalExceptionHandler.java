package com.lab.util;

import com.lab.vo.ResultVO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResultVO<?> handleValid(Exception e) {
        String msg = "";
        if (e instanceof MethodArgumentNotValidException ex) {
            msg = ex.getBindingResult().getFieldError() == null ?
                    "参数校验失败" :
                    ex.getBindingResult().getFieldError().getDefaultMessage();
        } else if (e instanceof BindException bx) {
            msg = bx.getBindingResult().getFieldError() == null ?
                    "参数校验失败" :
                    bx.getBindingResult().getFieldError().getDefaultMessage();
        }
        return ResultVO.fail(msg);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO<?> handleIllegalArgument(IllegalArgumentException e) {
        return ResultVO.fail(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResultVO<?> handleUsernameNotFound(UsernameNotFoundException e) {
        return ResultVO.fail("用户不存在");
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<?> handle(Exception e) {
        return ResultVO.fail(e.getMessage());
    }
}
