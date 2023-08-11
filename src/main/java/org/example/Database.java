package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Database {

    public Database(){

    }

    public boolean findId(String id){
        return Objects.equals(id,"100");
    }

    /*
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("data.json");

        try {
            // Leer JSON desde el archivo y convertirlo en un mapa
            Map<String, Object> jsonData = objectMapper.readValue(jsonFile, HashMap.class);

            // Modificar datos en el mapa
            jsonData.put("nuevoCampo", "Nuevo valor");

            // Escribir el mapa modificado de nuevo al archivo JSON
            objectMapper.writeValue(jsonFile, jsonData);

            System.out.println("Lectura y escritura de JSON completada exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}
