package com.quantumslots.physhacks.service;

public class TimeService {
    private static TimeService instance = new TimeService();
    private long startTimeMillis;

    private TimeService() {
        // Initialize the start time to the current time when the class is loaded
        startTimeMillis = System.currentTimeMillis();
    }

    public static TimeService getInstance() {
        return instance;
    }

    public void resetTime() {
        startTimeMillis = System.currentTimeMillis();
    }

    public long getCurrentTimeMillis() {
        return System.currentTimeMillis() - startTimeMillis;
    }
}
