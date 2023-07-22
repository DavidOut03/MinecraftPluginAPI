package com.davidout.api.minecraft.database;

import com.davidout.api.minecraft.database.result.ActionResult;
import com.davidout.api.minecraft.database.result.DatabaseDetails;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public abstract class Database {

    private final DatabaseDetails details;

    public Database(String name, String host, int port, String userName, String password) {
       this.details = new DatabaseDetails(name, host, port, userName, password);
    }


    public abstract ActionResult<Boolean> connect(DatabaseDetails details) throws Exception;
    public abstract ActionResult<Boolean> disconnect() throws Exception;
    public abstract ActionResult<Boolean> createTable(String tableName, Map<String, Class<?>> columns) throws Exception;
    public abstract ActionResult<Table> getData(String tableName) throws ExecutionException, InterruptedException, Exception;
    public abstract ActionResult<Boolean> setData(String tableName, Table data) throws ExecutionException, InterruptedException, Exception;
    public abstract ActionResult<Boolean> updateDatabaseValue(String tableName, String column, String newValue) throws Exception;


    public DatabaseDetails getDetails() {
        return details;
    }


}
