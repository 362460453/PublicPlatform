package com.elasticjob.springboot.service;

import java.util.List;

/**
 * @author yanlin
 * @version v1.0
 * @className SimpleJobA
 * @description 接口
 * @date 2019-07-01 1:19 PM
 **/
public interface SimpleJobA {
    void updateState(List<Integer> id);

    List<Integer> selectId(Integer count, Integer item);
}
