package org.example;
import java.sql.*;
import java.sql.SQLException;
import java.util.Locale;


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
        String cryptedPassword = Crypto.encrypt(password);
        Account account = null;
        String selectQuery = "SELECT number,owner_nit,debt,balance,active FROM accounts WHERE username = '" + username.toUpperCase() + "' AND password = '" + cryptedPassword + "'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getString("active").equals("1")) {
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

    public void changePassword(String username,String password){
        String cryptedPassword = Crypto.encrypt(password);
        String insertQuery = "UPDATE accounts SET password = ? WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, cryptedPassword);
            preparedStatement.setString(2, username.toUpperCase());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(String account_number){
        String insertQuery = "UPDATE accounts SET active = 0 WHERE number = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, account_number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void PQR(String nit, String context){
        String insertQuery = "INSERT INTO pqrs (nit, context) VALUES (? , ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nit);
            preparedStatement.setString(2, context);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Account createAccount(String nit, String username, String password) {
        Account account = null;
        String number = getAccountNumber();
        String insertQuery = "INSERT INTO accounts (number, owner_nit, username, password) VALUES (? , ? , ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, number);
            preparedStatement.setString(2, nit);
            preparedStatement.setString(3, username.toUpperCase());
            preparedStatement.setString(4, password);

            account = preparedStatement.executeUpdate() > 0 ? new Account(number,nit,username,0,0) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return account;
    }


    public boolean usernameExist(String username){
        String selectQuery = "SELECT username FROM accounts WHERE username = '" + username.toUpperCase() + "'";
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
            preparedStatement.setString(2, name.toUpperCase());
            preparedStatement.setString(3, surname.toUpperCase());
            preparedStatement.setString(4, email.toUpperCase());

            user = preparedStatement.executeUpdate() > 0 ? new User(nit.toUpperCase(), name.toUpperCase(), surname.toUpperCase(), email.toUpperCase()) : null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }


    public static void main(String[] args) {

    }

}
