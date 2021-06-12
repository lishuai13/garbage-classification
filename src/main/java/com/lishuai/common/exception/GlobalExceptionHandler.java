package com.lishuai.common.exception;

import com.lishuai.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 捕获全局异常
 * @author lishuai
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result handler(Exception e) {
        log.error("异常：----------------{}", e.getMessage());
        return Result.fail(400,e.getMessage(), null);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e) {

        log.error("运行时异常：----------------{}", e.getMessage());
        return Result.fail(401,e.getMessage(),null);
    }

    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e) {
        log.error("shiro异常：----------------{}", e.getMessage());
        return Result.fail(402,e.getMessage(), null);
    }



    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e) {
        log.error("Assert异常：----------------{}", e.getMessage());
        return Result.fail(403,e.getMessage(),null);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        log.error("实体校验异常：----------------{}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, Object> errorsMap = new HashMap<>();
        // 如果校验时有不符合校验规则的情况出现，springMVC会将错误信息放在BindingResult对象的错误提示信息里面
        String message = null;
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                message=error.getDefaultMessage()+"\n";
                System.out.println("错误消息提示：" + error.getDefaultMessage());
                System.out.println("错误的字段是？" + error.getField());
                System.out.println(error);
                System.out.println("------------------------");
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }
        }
        //todo 这里尸体校验的异常信息过长，需要自定义
        return Result.fail(404, message,errorsMap);
    }

}
