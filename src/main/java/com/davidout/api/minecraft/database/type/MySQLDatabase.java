package com.davidout.api.minecraft.database.type;

import com.davidout.api.minecraft.database.Database;
import com.davidout.api.minecraft.database.Table;
import com.davidout.api.minecraft.database.result.ActionResult;
import com.davidout.api.minecraft.database.result.DatabaseDetails;
import com.davidout.api.minecraft.database.exceptions.TableCreateException;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class MySQLDatabase extends Database {

    private Connection connection;

    public MySQLDatabase(String name, String host, int port, String userName, String password) {
        super(name, host, port, userName, password);
    }

    @Override
    public ActionResult<Boolean> connect(DatabaseDetails details) throws Exception {
        return new ActionResult<>(() -> {
            if(connection != null) this.disconnect();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://$host/$database".replace("$host", details.getHost()).replace("$database", details.getDatabaseName()), details.getUserName(), details.getPassword());
                return true;
            } catch (Exception e) {
                return false;
            }

        });
    }

    @Override
    public ActionResult<Boolean> disconnect() throws Exception {
        return new ActionResult<>( () -> {
            if(connection == null) return true;
            try {
                connection.close();
                connection = null;
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    @Override
    public ActionResult<Boolean> createTable(String tableName, Map<String, Class<?>> columns) throws Exception {
        if(connection == null) {
            throw new TableCreateException(tableName, "Database is not connected.");
        }

        return new ActionResult<>(() -> {
            if(connection == null) return false;
            String query = "CREATE TABLE $tableName($parameters)";

            StringBuilder parameterBuilder = new StringBuilder();

            columns.forEach((s, aClass) -> {
                parameterBuilder.append(",");
                parameterBuilder.append(s).append(" ").append(getSQLType(aClass));
            });

           ResultSet set = query(query.replace("$parameters", parameterBuilder.toString()));

            return null;
        });
    }

    @Override
    public ActionResult<Table> getData(String tableName) throws Exception {
        return new ActionResult<>(() -> {
            if(connection == null) return null;

            return null;
        });
    }

    @Override
    public ActionResult<Boolean> setData(String tableName, Table data) throws Exception {
        return new ActionResult<>(() -> {
            if(connection == null) return false;

            return null;
        });
    }

    @Override
    public ActionResult<Boolean> updateDatabaseValue(String tableName, String column, String newValue) throws Exception {
        return new ActionResult<>(() -> {
            if(connection == null) return false;

            return null;
        });
    }

    private String getSQLType(Class<?> cc) {
        String returned = "LONGVARCHAR";

        if (cc.equals(int.class)) {
            returned = "INT";
        }

        if(cc.equals(boolean.class)) {
            returned = "BOOLEAN";
        }

        if(cc.equals(double.class)) {
            returned = "DECIMAL";
        }

        if(cc.equals(float.class)) {
            return "FLOAT";
        }



        return returned;
    }

    private ResultSet query(String query, Object... parameters) throws Exception {
            PreparedStatement ps = connection.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i + 1, parameters[i]);
            }
           return ps.executeQuery();
    }


}
