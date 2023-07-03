package org.example;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final Database INSTANCE = new Database();
    private Connection conn;

    private Database() {
        try{
            String dbUrl = "jdbc:h2:./test";
            conn = DriverManager.getConnection(dbUrl);
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return Database.getInstance().getConnection();
    }

    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int executeUpdate(String sql){
        try(Statement st = conn.createStatement();){
            return st.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static synchronized Database getInstance() {
        return INSTANCE;
    }


}
