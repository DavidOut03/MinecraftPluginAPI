package com.davidout.api.minecraft.database.result;

import com.davidout.api.minecraft.MinecraftPlugin;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ActionResult<T> {

    private State state;
    private T result;

    public ActionResult(Callable<T> task) throws ExecutionException, InterruptedException {
        this.state = State.RUNNING;
        this.executeTask(task);
    }

    private void executeTask(Callable<T> task) throws ExecutionException, InterruptedException {
        FutureTask<T> futureTask = new FutureTask<>(task);
        new Thread(futureTask).start();
        this.result = futureTask.get();
        this.state = State.FINISHED;
    }

    public State getState() {return state;}
    public T getResult() {
        if(state == State.RUNNING) {
            throw new IllegalStateException("Database action is not finished yet.");
        }

        return result;
    }


    public enum State {
        RUNNING, FINISHED;
    }
}
