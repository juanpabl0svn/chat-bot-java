package org.example;

import java.util.Objects;

public class Bot {

    public Bot(){

    }

    public boolean findUser(String id){
        return Objects.equals(id, "1000");
    }
}
