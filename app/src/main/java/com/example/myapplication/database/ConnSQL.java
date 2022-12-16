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
            Log.d("ERROR LOGIN DATABASE", "getConnection: ");
        }catch(Exception e) {
            Log.e("ERROR LOGIN DATABASE", ""+e);
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
            rs = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(query);
        }catch (SQLException e){
            Log.d("11111111", "getSetWithoutEle: "+e);
        }
        return rs;
    }
    public ResultSet executeQ(String query){
        ResultSet rs = null;
        try{
            rs = conn.createStatement().executeQuery(query);
        }catch(SQLException e){
            Log.d("ERROR EXECUTE QUERY", "exec: "+e);
        }
        return rs;
    }
    public void updateSet(String table, String col, String where){
        try{
            conn.createStatement().executeQuery("UPDATE "+ table +" SET "+col+" WHERE "+where);
        }catch (SQLException e) {
            Log.d("ERROR UPDATE SET", "updateSet: "+e);
        }
    }
}

