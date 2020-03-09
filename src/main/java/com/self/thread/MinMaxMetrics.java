package com.self.thread;

import java.util.Random;

public class MinMaxMetrics {
    public static void main(String[] args) {
        Checker check = new Checker();
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    check.addSample(new Random().nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(check.getMax());
            }
        });
        t1.start();
        t2.start();
    }
}

class Checker {
    private volatile long minValue;
    private volatile long maxValue;

    /**
     * Initializes all member variables
     */
    public Checker() {
        this.maxValue = Long.MIN_VALUE;
        this.minValue = Long.MAX_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) throws InterruptedException {
        System.out.println("First thread called");
        synchronized (this) {
            System.out.println("Updating value");
            Thread.sleep(5000);
            this.minValue = Math.min(newSample, this.minValue);
            this.maxValue = Math.max(newSample, this.maxValue);
            System.out.println("value updated");
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        return this.minValue;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        return this.maxValue;
    }
}
