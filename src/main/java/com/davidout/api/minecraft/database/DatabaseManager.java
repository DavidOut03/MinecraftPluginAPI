package com.davidout.api.minecraft.database;

import com.davidout.api.minecraft.thread.Thread;
import com.davidout.api.minecraft.thread.ThreadHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DatabaseManager {

    private final Thread databaseThread;

    public DatabaseManager() {
        this.databaseThread = new Thread("database");
    }

    public Thread getDatabaseTaskHandler() {
        return databaseThread;
    }

}
