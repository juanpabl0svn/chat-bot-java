package org.example;
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
        String selectQuery = "SELECT number,owner_nit,debt,balance FROM accounts WHERE username = '" + username + "' AND password = '" + password + "'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String number = resultSet.getString("number");
                String owner_nit = resultSet.getString("owner_nit");
                float debt = resultSet.getFloat("debt");
                float balance = resultSet.getFloat("balance");
                account = new Account(number,owner_nit,username,balance,debt);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }



    public String getAccountNumber() {
        long randomNumber;
        String randomNumberString;
        do {
            randomNumber = Math.round((Math.random() * 9000000000L + 1000000000L));
            randomNumberString = Long.toString(randomNumber);
        }while(accountNumberExist(randomNumberString));
        return randomNumberString;
    }


    public Account createAccount(String nit, String username, String password) {
        Account account = null;
        String number = getAccountNumber();
        String insertQuery = "INSERT INTO accounts (number, owner_nit, username, password) VALUES (? , ? , ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, number);
            preparedStatement.setString(2, nit);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);

            account = preparedStatement.executeUpdate() > 0 ? new Account(number,nit,username,0,0) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return account;
    }


    public boolean usernameExist(String username){
        String selectQuery = "SELECT username FROM accounts WHERE username = '" + username + "'";
        boolean exist = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            exist = resultSet.next();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public boolean accountNumberExist(String accountNumber){
        String selectQuery = "SELECT number FROM accounts WHERE number = '" + accountNumber + "'";
        boolean exist = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            exist = resultSet.next();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;

    }


    public User getUserById(String nit) {
        User cliente = null;
        String selectQuery = "SELECT name,surname,email FROM users WHERE nit = " + nit;
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
        String insertQuery = "INSERT INTO users (nit,name,surname,email) VALUES (? , ? , ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nit);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, email);

            user = preparedStatement.executeUpdate() > 0 ? new User(nit, name, surname, email) : null;

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

        String number = dbManager.getAccountNumber();
        System.out.println(number);
    }

}
