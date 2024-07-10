package com.ethan.annotation;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;


/**
 * 用于标记防止重复提交的方法
 * @author Johnson
 */
@Inherited      // 如果一个类继承了 @Inherited 注解修饰的注解, 那么其子类也会继承这个注解
@Documented
@Retention(RetentionPolicy.RUNTIME)     // 使得该注解可以使用反射得到
@Target({ElementType.TYPE, ElementType.METHOD}) // 该注解可以对 类 和 方法 起作用
public @interface PreventDuplicateSubmit {

    /**
     * 判断重复提交到时间间隔(ms)
     * @return
     */
    int interval() default 10;

    /**
     * 提示消息
     * @return
     */
    String message() default "不允许重复提交, 请稍后再试";
}
