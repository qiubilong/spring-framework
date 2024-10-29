package com.experiment.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
/* Controller增强器
   异常处理- @ExceptionHandler
   数据绑定- @InitBinder
   预处理- @ModelAttribute
  */
public class GlobalExceptionHandler {

   @ExceptionHandler(BindException.class)/* 处理该异常 */
   @ResponseStatus(HttpStatus.NOT_FOUND)
   public String handleBindException(BindException ex) {
	   return ex.getMessage();
   }
}