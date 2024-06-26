package com.example.shopdemo;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MSSQL {
    Connection connection = null;
    private boolean _isOpened=false;
    String ipaddress, db, username, password;
    public static Connection connect;
    Statement st;
    public boolean isOpened()
    {
        return _isOpened;
    }

    @SuppressLint("NewApi")
    private Connection ConnectionHelper(String user, String password, String database, String server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + server + ";" + "databaseName=" + database + ";user=" + user + ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return connection;
    }
    public MSSQL() {//設定jdbc連結字串，請依你的SQL Server設定值修改
        try {
            ipaddress = "192.168.31.1:7788";
            db= "ConnectTest";
            username = "Mew";
            password = "123";
            connect = ConnectionHelper(username, password, db, ipaddress);
            if (connect.isClosed()==false)
            {
                _isOpened=true;
            }
            else
            {
                _isOpened=false;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}