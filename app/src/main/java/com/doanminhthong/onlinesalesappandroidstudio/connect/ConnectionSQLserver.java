package com.doanminhthong.onlinesalesappandroidstudio.connect;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSQLserver {
    Connection connection;
    String username, pass, ip, port, database;
    @SuppressLint("NewApi")

    public Connection connectionClass(){
        ip = "192.168.1.125";
        database = "AppAndroid_SaleOnline";
        username = "sa";
        pass = "12345678";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + database + ";user=" +  username + ";password" + pass + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }

        return  connection;
    }
}
