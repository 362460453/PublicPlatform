package com.elasticjob.service.impl;

import com.elasticjob.service.TestDI;
import org.springframework.stereotype.Service;

/**
 * @author yanlin
 * @version v1.0
 * @className TestDIImpl
 * @description TODO
 * @date 2019-07-01 10:03 AM
 **/
@Service
public class TestDIImpl implements TestDI {
    @Override
    public void testSpring() {
        System.out.println("得到注入对象");
    }
}
