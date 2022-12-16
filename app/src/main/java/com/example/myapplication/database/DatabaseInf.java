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
        String ip = "192.168.3.41";        // switch this ip to your ip address
>>>>>>> 9040042720e20d76b2e354cfdc6aa4a987ab4f1a
        this.url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db + ";user=" + username + ";"+"password=" + password + ";";
    }

    public String getDb(){
        return url;
    }

}
