package com.self.thread;


import java.util.List;

/*
In this exercise we are going to implement a MultiExecutor
        The client of this class will create a list of Runnable tasks and provide that list into MultiExecutor's constructor.
        When the client runs the .executeAll(), MultiExecutor, will execute all the given tasks.
        To take full advantage of our multicore CPU, we would like the MultiExecutor to execute all the tasks concurrently, by passing each task to a different thread.
*/


public class MultiExecutor {

    // Add any necessary member variables here
    List<Runnable> tasks;

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        // Complete your code here
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        // complete your code here
        Thread thread;
        for (Runnable task : tasks) {
            thread = new Thread(task);
            thread.start();
        }
    }
}
