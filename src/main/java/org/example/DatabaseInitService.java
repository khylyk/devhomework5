package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabaseInitService {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        Path myPath = Paths.get("sql/init_db.sql");
        try{
            String sql = String.join("/n", Files.readAllLines(myPath));
            database.executeUpdate(sql);
        } catch(Exception e){
            e.printStackTrace();
        }


    }
}
