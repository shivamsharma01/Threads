package com.self.thread;

// separate object for thread and runnable
public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            throw new RuntimeException();
        });
        System.out.println("Before starting the thread "+Thread.currentThread().getName());
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("A critical error"+t.getName() + "the error is "+e.getMessage());
        });
        thread.start();
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setName("CustomThread");
        System.out.println("After starting the thread "+Thread.currentThread().getName());
    }
}
