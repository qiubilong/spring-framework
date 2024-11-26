package com.experiment.test.mybatis.test.service;

import com.experiment.test.mybatis.test.entity.MomentCounter;
import com.experiment.test.mybatis.test.mapper.MomentCommentExtMapper;
import com.experiment.test.mybatis.test.mapper.MomentCounterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxuegui
 * @since 2024/11/21
 */
@Component
public class MomentService {

    @Autowired
    private MomentCommentExtMapper momentCommentExtMapper;

    @Autowired
    private MomentCounterMapper momentCounterMapper;/* MapperProxy -->SqlSessionTemplate.sqlSessionInterceptor --> ThreadLocal获取DefaultSqlSession,不存在新建 --> 实现事务和线程安全  */

    public void batchInsertCounter(){
        List<MomentCounter> list = new ArrayList<>();
        list.add(new MomentCounter(System.currentTimeMillis()));
        list.add(new MomentCounter(System.currentTimeMillis()+100));
        Long userId = 500350L;
        boolean op = momentCounterMapper.batchInsertCounter(list, userId);
        System.out.println("batchInsertCounter="+op);
    }

    public void selectMomentLikeNum(){
        ArrayList<Long> list = new ArrayList<Long>();
        list.add(11L);
        list.add(22L);
        momentCounterMapper.selectMomentLikeNum(list);
    }

    public boolean insertCounter(){
        return momentCounterMapper.insertCounter(new MomentCounter(System.currentTimeMillis()));
    }
}
