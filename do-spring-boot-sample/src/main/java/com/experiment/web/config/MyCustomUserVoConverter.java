package com.experiment.web.config;

import com.experiment.web.vo.UserVO;
import org.springframework.core.convert.converter.Converter;

/* 自定义类型转换器 */
public class MyCustomUserVoConverter implements Converter<String, UserVO> {
       @Override
       public UserVO convert(String source) {
           // 实现转换逻辑
		   UserVO userVo = new UserVO();
		   userVo.setName(source);
           return userVo;
       }
   }