package org.example;

import java.security.PublicKey;
import java.sql.*;
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


    public Account getAccount(String username, String password) {
        Account account = null;
        String selectQuery = "SELECT * FROM users WHERE username = " + username + "AND password = " + password;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                account = new Account();
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }


    public Account createAccount(String nit, String username, String password) {
        User user = null;
        String insertQuery = "INSERT INTO users (nit,name,surname) VALUES (? , ? , ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nit);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);

            int rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return new Account();
    }


    public boolean usernameExist(String username){
        return true;

    }


    public User getUserById(String nit) {
        User cliente = null;
        String selectQuery = "SELECT * FROM users WHERE nit = " + nit;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");

                cliente = new User(nit, name, surname, email);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public User createUser(String nit, String name, String surname, String email) {
        User user = null;
        String insertQuery = "INSERT INTO users (nit,name,surname) VALUES (? , ? , ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nit);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, email);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                user = new User(nit, name, surname, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }


    public float getBalance(String id) {
        float balance = -1;
        String selectQuery = "SELECT a.balance as balance " +
                "FROM users u " +
                "JOIN accounts a ON u.nit = a.owner_nit " +
                "WHERE u.nit = " + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                balance = resultSet.getFloat("balance");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {

            System.out.println(e);
            e.printStackTrace();
            return balance;
        }

        return balance;

    }

    public float getDebt(String id) {
        float debt = -1;
        String selectQuery = "SELECT a.debt as debt " +
                "FROM users u " +
                "JOIN accounts a ON u.nit = a.owner_nit " +
                "WHERE u.nit = " + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                debt = resultSet.getFloat("debt");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {

            System.out.println(e);
            e.printStackTrace();
            return debt;
        }

        return debt;

    }

    /*
    public float payDebt(String id){

    }

     */


    public static void main(String[] args) {
        Database dbManager = new Database();
    }

}
