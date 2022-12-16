package com.example.myapplication.database;

public class DatabaseInf {
    private final String url;

    public DatabaseInf() {
        String password = "123456";
        String username = "sceddd";
        String db = "TravelDBS";
        String port = "1433";
<<<<<<< HEAD
        String ip = "192.168.3.69";        // switch this ip to your ip address
=======
        String ip = "192.168.3.64";        // switch this ip to your ip address
>>>>>>> c3239440ffcca3c72f486bb4e63065e1aed89dd8
        this.url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db + ";user=" + username + ";"+"password=" + password + ";";
    }

    public String getDb(){
        return url;
    }

}
