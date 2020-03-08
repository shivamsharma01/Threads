package com.self.thread;

import sun.rmi.runtime.Log;

import java.math.BigInteger;

public class InterruptThread {
    public static void main(String[] args) {
        Thread t = new Thread(new LongComputationTask(new BigInteger("28716378163781"),new BigInteger("1016371")));
        //thread
        t.setDaemon(true); // prevents a thread from blocking our app from exiting
        t.start();
        // either handle using Thread.currentThread().isInterrupted() or Interrupted exception
        // if daemon is set to true, no need to handle the interrupt
        t.interrupt();
    }
    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base+"^"+power+" = "+pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                // below condition is removed if daemon thread is set to true
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Computation interrupted prematurely");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}
