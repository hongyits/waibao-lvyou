package com.ruoyi.common.utils.file;

import java.util.HashSet;
import java.util.Set;

/**
 * 外部流水号生成器(雪花算法)
 * @Author:fjc
 * @Description:
 * @Date: 2018/9/17
 **/
public class OutNoGenerator {

    public static String nextOutNo(){
        return "" + OutNoGenerator.getIdWorker().nextNo();
    }


    public synchronized long nextNo(){
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

    private final long twepoch = 1288834974657L;//起始的时间戳
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final long sequenceBits = 12L;
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    public static OutNoGenerator idWorker = null;

    public static OutNoGenerator getIdWorker(){
        if(null ==  idWorker){
            idWorker = new OutNoGenerator(0, 0);
        }
        return idWorker;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    private OutNoGenerator(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public static void main(String[] args) {
        Long ss = System.currentTimeMillis();
        Set<String> set = new HashSet<String>() ;
        for (int i = 0; i < 10000; i++) {
            String id = OutNoGenerator.nextOutNo();
            System.out.println(i+":"+id);
            set.add(id);
        }
        System.out.println(System.currentTimeMillis()-ss);
        System.out.println(set.size());
    }
}
