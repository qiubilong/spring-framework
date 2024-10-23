package com.experiment.web.config;

import com.experiment.web.vo.UserVo;
import org.springframework.core.convert.converter.Converter;

/* 自定义类型转换器 */
public class MyCustomUserVoConverter implements Converter<String, UserVo> {
       @Override
       public UserVo convert(String source) {
           // 实现转换逻辑
		   UserVo userVo = new UserVo();
		   userVo.setName(source);
           return userVo;
       }
   }