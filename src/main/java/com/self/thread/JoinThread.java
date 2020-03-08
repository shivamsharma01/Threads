package com.self.thread;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// thread coordination and thread join
public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(0L, 1L, 2L, 34323425L, 2324L, 4656L, 23L, 5556L);
        List<FactorialThread> threads = new ArrayList<>();

        for (long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread thread: threads) {
            thread.setDaemon(true);
            thread.start();
        }

        // main thread will wait for threads to finish execution
        for (Thread thread: threads) {
            thread.join(2000); // max wait 2000 before returning
            //thread.join(); // wait indefinitely
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation of " + inputNumbers.get(i) + " is still is progress..");
            }
        }
    }

    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            super.setPriority(Thread.MAX_PRIORITY);
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
            System.out.println("Finishing " +inputNumber);
        }

        private BigInteger factorial(long inputNumber) {
            BigInteger tempResult = BigInteger.ONE;
            for (long i = inputNumber; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }

    }

}
