package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义限流注解
 *
 * @author yanlin
 * @version v1.3
 * @date 2019-04-05 7:58 PM
 * @since v8.0
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    /**
     * 限流唯一标示
     *
     * @return
     */
    String key() default "limit";

    /**
     * 限流时间
     *
     * @return
     */
    int time() default 5;

    /**
     * 限流次数
     *
     * @return
     */
    int count() default 5;
}
