package com.itheima.reggie_take_out.common;

/**
 * @author: xu
 * @email: xxx@xx.com
 * @date: 2022/8/13 10:16
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
