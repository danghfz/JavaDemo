package com.dhf.utils.ut;

import org.lionsoul.ip2region.xdb.Searcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    // .g
    static {
        // 1、创建 searcher 对象
        InputStream inputStream = IpUtil.class.getResourceAsStream("/ip2region.xdb");
        if (inputStream != null) {
            try {
                searcher = Searcher.newWithBuffer(inputStreamToByteArray(inputStream));
                STATUS = true;
            } catch (IOException e) {
                System.err.printf("failed to create searcher with ip2region.xdb: %s\n", e);
            }
        }
    }

    public static String getCityByIp(String ip) {
        if (!STATUS) {
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
            return "failed to search(" + ip + "): " + e;
        }
    }

    private static byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }
}
