package com.experiment.web.config;

import com.experiment.web.vo.UserVO;

import java.beans.PropertyEditorSupport;

public class UserVoEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        // 将字符串转换为UserVo对象
        UserVO myType = new UserVO();
        myType.setName(text);
        setValue(myType);
    }
}