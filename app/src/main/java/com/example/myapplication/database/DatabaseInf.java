package com.example.myapplication.database;

public class DatabaseInf {
    private final String url;

    public DatabaseInf() {
        String password = "123456";
        String username = "sceddd";
        String db = "TravelDBS";
        String port = "1433";
        String ip = "192.168.0.103";        // switch this ip to your ip address
        this.url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db + ";user=" + username + ";"+"password=" + password + ";";
    }
    public String getDb(){
        return url;
    }
}
