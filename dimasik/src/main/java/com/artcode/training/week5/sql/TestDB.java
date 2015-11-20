package com.artcode.training.week5.sql;

import com.artcode.myproject.model.RentalRequirements;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TestDB {

    public static final String URL = "jdbc:postgresql://localhost:5432/myrent";
    public static final String USER = "postgres";
    public static final String PASSWORD = "root";
    public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    public static final String INSERT_REQUIREMENTS_QUERY = "INSERT INTO requirements (appartmentsType, description, cost) VALUES %s";
    public static final String SELECT_ALL_REQUIREMENTS_QUERY = "SELECT * FROM requirements";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(POSTGRESQL_DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        printTable(statement);
    }

    private static void addRequirements(Statement statement, RentalRequirements... requirements) throws SQLException {
        List<String> collect = Arrays.stream(requirements).flatMap(requirement ->
                requirement.getSuitableTypes().stream().map(
                        appartmentsType -> String.format("('%s','%s',%s)", appartmentsType, requirement.getDescriprion(), requirement.getCost()))).collect(toList());
        statement.executeUpdate(String.format(INSERT_REQUIREMENTS_QUERY, String.join(",", collect)));
    }

    private static void printTable(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_REQUIREMENTS_QUERY);
        while (resultSet.next()) {
            System.out.printf("id %d, appartmentsType %s, description:  %s, cost = %d\n",
                    resultSet.getInt("id"),
                    resultSet.getString("appartmentsType"),
                    resultSet.getString("description"),
                    resultSet.getInt("cost"));
        }
    }
}
