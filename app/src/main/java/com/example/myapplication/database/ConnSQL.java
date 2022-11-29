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
    public Connection getConnection() {
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
    
    public ResultSet getFullSet(String table){
        return getSetWithoutEle(table,"*");
    }
    public ResultSet getSetWithoutEle(String table, String eles){
        return getSetWithoutEle(table,eles,"");
    }
    public ResultSet getSetWithoutEle(String table, String eles,String where){
        ResultSet rs = null;
        try {
            String query = "SELECT " + eles + " FROM " +table +" WHERE "+where;
            if (where.equals(""))
                query = query.replace(" WHERE "+where,"");
            Log.d("111111111", "getSetWithoutEle: "+query);
            rs = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(query);
        }catch (SQLException e){
            Log.d("11111111", "getSetWithoutEle: "+e);
        }
        return rs;
    }
    public void add(String query){
        try{
            conn.createStatement().execute(query);
        }catch(SQLException e){
            Log.d("111111111111", "add: "+e);
        }
    }
    public void updateSet(String table, String col, String where){
        try{
            conn.createStatement().executeQuery("UPDATE "+ table +" SET "+col+" WHERE "+where);
        }catch (SQLException e) {
            Log.d("Error Update Set", "updateSet: "+e);
        }
    }
}

