package org.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class DatabasePopulateService {
    public static void main(String[] args) {
        Database database = Database.getInstance();

        String[] names = {"Олександр", "Юлія", "Максим", "Оксана", "Андрій", "Софія", "Іван", "Олена", "Володимир", "Анастасія"};
        String[] birthdays = {"1990-05-15", "1985-12-10", "1982-09-23", "1978-07-18", "1992-03-08", "1991-01-25", "1987-11-02", "1984-06-14", "1979-04-07", "1989-02-28"};
        String[] levels = {"Trainee", "Junior", "Middle", "Senior", "Middle", "Trainee", "Junior", "Senior", "Middle", "Junior"};
        String[] clients = {"КиївЕнерго", "Комфі", "Нова Пошта", "Укрзалізниця", "Укрпошта"};
        int[] clientIds = {1, 2, 3, 4, 5, 1, 3, 2, 4, 5};
        String[] startDates = {"2023-01-01", "2022-11-10", "2023-03-15", "2023-02-01", "2022-12-20", "2023-05-01", "2023-06-10", "2023-04-15", "2023-03-01", "2023-01-20"};
        String[] endDates = {"2023-04-15", "2023-02-28", "2023-07-31", "2023-05-20", "2023-06-30", "2024-01-15", "2023-09-30", "2023-07-31", "2023-06-20", "2023-07-15"};
        int[] projectIds = {1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10};
        int[] workerIds = {1, 2, 3, 1, 2, 3, 2, 3, 4, 10, 5, 7, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3};
        int[] salaries = {800, 1200, 2500, 5000, 3000, 900, 1100, 4500, 2800, 1500};

        try{

            String worker_insert = "INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)";
            String client_insert = "INSERT INTO client (name) VALUES (?)";
            String project_insert = "INSERT INTO project (client_id, start_date, finish_date) VALUES (?, ?, ?)";
            String project_worker_insert = "INSERT INTO project_worker (project_id, worker_id) VALUES (?, ?)";

            PreparedStatement queryStatement1 = database.getConnection().prepareStatement(worker_insert);
            PreparedStatement queryStatement2 = database.getConnection().prepareStatement(client_insert);
            PreparedStatement queryStatement3 = database.getConnection().prepareStatement(project_insert);
            PreparedStatement queryStatement4 = database.getConnection().prepareStatement(project_worker_insert);

            for(int i = 0; i < names.length; i++){
                queryStatement1.setString(1, names[i]);
                queryStatement1.setDate(2, java.sql.Date.valueOf(LocalDate.parse(birthdays[i])));
                queryStatement1.setString(3, levels[i]);
                queryStatement1.setInt(4, salaries[i]);
                queryStatement1.addBatch();
            }

            queryStatement1.executeBatch();

            for(int i = 0; i < clients.length; i++){
                queryStatement2.setString(1, clients[i]);
                queryStatement2.addBatch();
            }

            queryStatement2.executeBatch();

            for(int i = 0; i < clientIds.length; i++){
                queryStatement3.setInt(1, clientIds[i]);
                queryStatement3.setDate(2, java.sql.Date.valueOf(LocalDate.parse(startDates[i])));
                queryStatement3.setDate(3, java.sql.Date.valueOf(LocalDate.parse(endDates[i])));
                queryStatement3.addBatch();
            }

            queryStatement3.executeBatch();

            for(int i = 0; i < projectIds.length; i++){
                queryStatement4.setInt(1, projectIds[i]);
                queryStatement4.setInt(2, workerIds[i]);
                queryStatement4.addBatch();
            }

            queryStatement4.executeBatch();
        } catch(Exception e){
            e.printStackTrace();
        }


    }
}
