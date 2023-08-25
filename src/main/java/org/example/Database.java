package org.example;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static final String JDBC_URL = "jdbc:mysql://localhost/users";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";


    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet executeQuery(String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(String nit){
        User cliente = null;
        String selectQuery = "SELECT * FROM users WHERE nit = " + nit + ";";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");

                cliente = new User(nit,name,surname);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public User createUser(String nit,String name, String surname){
        User user = null;
        String insertQuery = "INSERT INTO users (nit,name,surname) VALUES (?, ?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nit);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0) {
                user =  new User(nit,name, surname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }


    public float getMyCurrency(String id){
        float currency = 0;
        String selectQuery = "SELECT * FROM users WHERE nit = " + id + ";";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                currency = resultSet.getFloat("currency");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currency;

    }

    public strig

    public static void main(String[] args) {
        Database dbManager = new Database();
    }

}
