package com.dhf.server;

import java.util.function.Supplier;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/25/0025 22:38
 * 简化版的锁服务，简化频繁的加锁
 */
public class LockServer {
    /**
     * 给一个方法进行加锁执行
     * @param
     */
    public <T> T execute(Supplier<T> supplier){
        synchronized (this.getClass()){
            return supplier.get();
        }
    }
}
