package org.example;

import java.beans.Statement;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public List<MaxProjectCountClient> findMaxProjectCountClient(){
        Database database = Database.getInstance();
        Path myPath = Paths.get("sql/find_max_projects_client.sql");
        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();
        try{
            String sql = String.join("/n", Files.readAllLines(myPath));
            Statement st = (Statement) database.getConnection().createStatement();
            ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);

            while(rs.next()){
                MaxProjectCountClient maxProjectCountClient = new MaxProjectCountClient();
                maxProjectCountClient.setProjectCount(Integer.parseInt(rs.getString("project_count")));
                maxProjectCountClient.setName(rs.getString("name"));
                maxProjectCountClients.add(maxProjectCountClient);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return maxProjectCountClients;
    }

    public List<LongestProject> findLongestProject(){
        Database database = Database.getInstance();
        Path myPath = Paths.get("sql/find_longest_project.sql");
        List<LongestProject> longestProjects = new ArrayList<>();
        try{
            String sql = String.join("/n", Files.readAllLines(myPath));
            Statement st = (Statement) database.getConnection().createStatement();
            ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);

            while(rs.next()){
                LongestProject longestProject = new LongestProject();
                longestProject.setProjectId(Integer.parseInt(rs.getString("id")));
                longestProject.setStartDate(LocalDate.parse(rs.getString("start_date")));
                longestProject.setEndDate(LocalDate.parse(rs.getString("end_date")));
                longestProjects.add(longestProject);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return longestProjects;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker(){
        Database database = Database.getInstance();
        Path myPath = Paths.get("sql/find_max_salary_worker.sql");
        List<MaxSalaryWorker> maxSalaryWorkers = new ArrayList<>();
        try{
            String sql = String.join("/n", Files.readAllLines(myPath));
            Statement st = (Statement) database.getConnection().createStatement();
            ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);

            while(rs.next()){
                MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker();
                maxSalaryWorker.setName(rs.getString("name"));
                maxSalaryWorker.setSalary(Integer.parseInt(rs.getString("salary")));
                maxSalaryWorkers.add(maxSalaryWorker);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return maxSalaryWorkers;
    }

    public List<YongestEldestWorkers> findYoungestEldestWorkers(){
        Database database = Database.getInstance();
        Path myPath = Paths.get("sql/find_youngest_eldest_workers.sql");
        List<YongestEldestWorkers> yongestEldestWorkers = new ArrayList<>();
        try{
            String sql = String.join("/n", Files.readAllLines(myPath));
            Statement st = (Statement) database.getConnection().createStatement();
            ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);

            while(rs.next()){
                YongestEldestWorkers yongestEldestWorker = new YongestEldestWorkers();
                yongestEldestWorker.setName(rs.getString("name"));
                yongestEldestWorker.setAge(Integer.parseInt(rs.getString("age")));
                yongestEldestWorkers.add(yongestEldestWorker);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return yongestEldestWorkers;
    }

    public List<ProjectPrice> printProjectPrices(){
        Database database = Database.getInstance();
        Path myPath = Paths.get("sql/find_youngest_eldest_workers.sql");
        List<ProjectPrice> projectPrices = new ArrayList<>();
        try{
            String sql = String.join("/n", Files.readAllLines(myPath));
            Statement st = (Statement) database.getConnection().createStatement();
            ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);

            while(rs.next()){
                ProjectPrice projectPrice = new ProjectPrice();
                projectPrice.setProjectPrice(Integer.parseInt(rs.getString("project_cost")));
                projectPrice.setId(Integer.parseInt(rs.getString("id")));
                projectPrices.add(projectPrice);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return projectPrices;
    }
}
