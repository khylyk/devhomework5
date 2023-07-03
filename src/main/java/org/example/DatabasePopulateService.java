package org.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class DatabasePopulateService {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        Path myPath = Paths.get("sql/populate_db.sql");
        String[] names = {"Олександр", "Юлія", "Максим", "Оксана", "Андрій", "Софія", "Іван", "Олена", "Володимир", "Анастасія"};
        String[] birthdays = {"1990-05-15", "1985-12-10", "1982-09-23", "1978-07-18", "1992-03-08", "1991-01-25", "1987-11-02", "1984-06-14", "1979-04-07", "1989-02-28"};
        String[] levels = {"Trainee", "Junior", "Middle", "Senior", "Middle", "Trainee", "Junior", "Senior", "Middle", "Junior"};
        int[] salaries = {800, 1200, 2500, 5000, 3000, 900, 1100, 4500, 2800, 1500};
        try{
            String sql = String.join("/n", Files.readAllLines(myPath));
            PreparedStatement queryStatement = database.getConnection().prepareStatement(sql);

            for(int i = 0; i < names.length; i++){
                queryStatement.setString(1, names[i]);
                queryStatement.setDate(2, java.sql.Date.valueOf(LocalDate.parse(birthdays[i])));
                queryStatement.setString(3, levels[i]);
                queryStatement.setInt(4, salaries[i]);
                queryStatement.addBatch();
            }

            queryStatement.executeBatch();
        } catch(Exception e){
            e.printStackTrace();
        }


    }
}
