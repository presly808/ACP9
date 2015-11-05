package ua.artcode.week5.jdbc;

import ua.artcode.week5.jdbc.utils.JDBCUtils;

import java.sql.*;

public class _01Into {


    public static final String PASSWORD = "root";
    public static final String USER = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/ACP9";
    public static final String SELECT_ALL = "SELECT id,name,phone FROM users";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                System.out.printf("id %d, name %s, phone %s\n",
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.clearCloseAll(connection, statement, resultSet);
        }


    }


}
