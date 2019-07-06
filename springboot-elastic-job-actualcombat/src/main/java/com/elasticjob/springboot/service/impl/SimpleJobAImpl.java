package com.elasticjob.springboot.service.impl;

import com.elasticjob.springboot.mapper.JobAMapper;
import com.elasticjob.springboot.service.SimpleJobA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanlin
 * @version v1.0
 * @className SimpleJobAImpl
 * @description TODO
 * @date 2019-07-01 1:20 PM
 **/
@Service
public class SimpleJobAImpl implements SimpleJobA {
    @Autowired
    JobAMapper jobAMapper;

    @Override
    public void updateState(List<Integer> id) {
         jobAMapper.updateState(id);
    }

    @Override
    public List<Integer> selectId(Integer count, Integer item) {

        return jobAMapper.selectId(count, item);
    }
}
