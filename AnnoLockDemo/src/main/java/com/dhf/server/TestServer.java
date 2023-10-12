package com.dhf.server;

import com.dhf.annotation.Lock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/26/0026 9:14
 */
@Component
public class TestServer {
    private static int threadCount = 10;
    private static int threadSleepTime = 3000;
    private static int end = 10000;
    private static LockServer lockServer = new LockServer();
    private static Object lock = new Object();
    // 多个线程调用同一个资源
    private static int count = 0;

    public void test() throws InterruptedException {
        /*
         *模拟多个线程调用同一个资源
         *在不加锁的情况下，count的值会出现错误
         * */
        extracted();
        /*
         * 通过synchronized进行加锁
         * */
        extractedBySynchronized();
        /*
         * 通过Server的封装，将执行内容进行加锁*/
        extractedByServer();
        /*
         * extractedByAnnotation
         * 通过注解的方式进行加锁
         */
        extractedByAnnotation();
    }

    /**
     * 不加锁的情况下，count的值会出现错误
     *
     * @throws InterruptedException
     */
    private static void extracted() throws InterruptedException {
        Obj obj = new Obj();
        // 结果不准确
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                add(obj);
            }).start();
        }
        Thread.sleep(threadSleepTime);
        System.out.println("extracted:" + obj.res);
    }

    /**
     * 加锁的情况下，count的值正确
     *
     * @throws InterruptedException
     */
    public static void extractedBySynchronized() throws InterruptedException {
        Obj obj = new Obj();
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                addSynchronized(obj);
            }).start();
        }
        Thread.sleep(threadSleepTime);
        System.out.println("extractedBySynchronized:" + obj.res);
    }

    public static void extractedByServer() throws InterruptedException {
        Obj obj = new Obj();
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                lockServer.execute(() -> {
                    add(obj);
                    return null;
                });
            }).start();
        }
        Thread.sleep(threadSleepTime);
        System.out.println("extractedByServer:" + obj.res);
    }

    public static void extractedByAnnotation() throws InterruptedException {
        Obj obj = new Obj();
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                addByAnnotation(obj);
            }).start();
        }
        Thread.sleep(threadSleepTime);
        System.out.println("extractedByAnnotation:" + obj.res);
    }

    @Lock
    private static void addByAnnotation(Obj obj) {
        for (int i = 0; i < end; i++) {
            obj.res++;
        }
    }

    public static void add(Obj obj) {
        for (int i = 0; i < end; i++) {
            obj.res++;
        }
    }

    /**
     * synchronized加锁的情况下，count的值正确
     *
     * @param obj
     */
    public static void addSynchronized(Obj obj) {
        synchronized (lock) {
            for (int j = 0; j < end; j++) {
                obj.res++;
            }
        }
    }

    static class Obj {
        public int res = 0;
    }
}
