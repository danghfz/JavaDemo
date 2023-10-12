package com.dhf.utils;

import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/14/0014 11:53
 */
public class IpUtil {
    private static Searcher searcher = null;
    private static final String UNKNOWN = "unknown";
    private static boolean STATUS = false;
    static {
        String dbPath = null;
        try {
            dbPath = Objects.requireNonNull(com.dhf.utils.IpUtil.class.getResource("/ip2region.xdb")).getPath();
            searcher = Searcher.newWithFileOnly(dbPath);
            STATUS = true;
        } catch (IOException e) {
            System.err.printf("failed to create searcher with `%s`: %s\n", dbPath, e);
        }
    }
    public static String getCityByIp(String ip) {
        if (!STATUS){
            return UNKNOWN;
        }
        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
            return region;
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
            return "failed to search("+ip+"): "+e;
        }
    }

    public static void main(String[] args) {

    }
}
