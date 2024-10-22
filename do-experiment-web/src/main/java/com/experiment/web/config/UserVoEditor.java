package com.experiment.web.config;

import com.experiment.web.vo.UserVo;

import java.beans.PropertyEditorSupport;

public class UserVoEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        // 将字符串转换为UserVo对象
        UserVo myType = new UserVo();
        myType.setName(text);
        setValue(myType);
    }
}