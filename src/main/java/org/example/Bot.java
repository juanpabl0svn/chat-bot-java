package org.example;

import java.sql.SQLException;
import java.util.Objects;

public class Bot {

    public static void main(String[] args) {
        Database db = new Database();
        String query = "SELECT * FROM usuarios";
        System.out.println(db.executeQuery(query));


    }


}
