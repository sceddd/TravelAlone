package com.example.myapplication.database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnSQL {
    DatabaseInf databaseInf = new DatabaseInf();
    public ConnSQL() {
        conn = getConnection();
    }

    Connection conn;
    // ----------------------------------------------------------------
    // connect to database
    // ----------------------------------------------------------------
    @SuppressLint("NewApi")
    private Connection getConnection() {
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String connectionURL;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = databaseInf.getDb();
            conn = DriverManager.getConnection(connectionURL);
        }catch(Exception e) {
            Log.e("error", ""+e);
        }
        return conn;
    }
    public ResultSet getSet(String table){
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM "+table;
            rs = conn.createStatement().executeQuery(query);
            Log.d("12345", "getSet: "+rs.getString(1));
        }
        catch (SQLException e) {
            Log.d("error", "onCreate: "+e);;
        }
        return rs;
    }
}

