package com.pengniao.cdzcharginginterface.util;
/**
 * @ClassNameIdWorker
 * @Description 雪花算法 生成ID
 * @Author
 * @Date2020/8/12 16:46
 * @Version V1.0
 **/
public class IdWorker {

    private static final long twepoch = 1288834974657L;
    private static final long workerIdBits = 5L;
    private static final long datacenterIdBits = 5L;
    //private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    //private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private static final long sequenceBits = 12L;
    private static final long workerIdShift = sequenceBits;
    private static final long datacenterIdShift = sequenceBits + workerIdBits;
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static final long workerId = 18;
    private static final long datacenterId = 01;
    private static long sequence = 0L;
    private static long lastTimestamp = -1L;

    /**
     * @param workerId : 机器ID 0--31
     * @param datacenterId : 业务ID  0--31

    public IdWorker(long workerId, long datacenterId) {
    if (workerId > maxWorkerId || workerId < 0) {
    throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
    }
    if (datacenterId > maxDatacenterId || datacenterId < 0) {
    throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
    }
    this.workerId = workerId;
    this.datacenterId = datacenterId;
    } */

    public static synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String.valueOf(IdWorker.nextId());
        }
        System.out.println(String.valueOf(IdWorker.nextId()));
        System.out.println(String.valueOf(IdWorker.nextId()));
        System.out.println(String.valueOf(IdWorker.nextId()));
        System.out.println(String.valueOf(IdWorker.nextId()));
        System.out.println("生成100万个订单耗时:" + (System.currentTimeMillis()-start) + "毫秒");
    }


}