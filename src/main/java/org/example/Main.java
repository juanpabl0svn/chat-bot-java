package org.example;

import java.sql.SQLException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int attemps = 0;


    public static void main(String[] args) throws SQLException {

        int isRegistered;

        Main main = new Main();


        System.out.println("Hola, yo soy tu asistente virtual" +
                ", ya estas registrado en Nu Bank? \n" +
                "1.Si\n" +
                "2.No\n" +
                "Otro.Salir\n");

        isRegistered  = scanner.nextInt();

        if (isRegistered == 1){
            main.logIn();
        }
        if (isRegistered == 2){

        }
        main.sayBye();



    }

    public void sayBye(){
        System.out.println("\nGracias por usar nuestro servicio " +
                "de asistente virtual, nos vemos pronto!!");
    }

    public boolean logIn(){
        String id;
        boolean loggedIn;
        int repeatLogIn;

        System.out.println("Ingrese por favor su numero de " +
                "cedula: \n");
        id = scanner.next();

        loggedIn = true;

        if (loggedIn){
            System.out.println("Logged In");
            return menu();
        }else{
            if (attemps < 2 ){
                System.out.println("\nUsuario no encontrado \n" +
                        "¿Desea intentarlo de nuevo?\n" +
                        "1.Si\n" +
                        "2.No\n" +
                        "otro.Salir\n");
                repeatLogIn = scanner.nextInt();

                if (repeatLogIn == 1){
                    attemps++;
                    return logIn();
                }
            }

            System.out.println("\nNumero maximo de intentos" +
                    "favor intentar mas tarde !!");


        }
        return false;
    }

    public boolean menu(){

        int option;
        String name = "juan";

        do{
            System.out.println("Bienvenido "+name+", estoy" +
                    " aqui para resolver tu dudas y inquietudes, " +
                    "selecciona una de las siguientes opciones:\n" +
                    "0.Salir" +
                    "1.Saldo pendiente\n" +
                    "2.Saldo en mi cuenta\n" +
                    "3.Cambiar información personal\n" +
                    "4.Eliminar cuenta\n" +
                    "5.Conocer creditos\n");
            option = scanner.nextInt();

        }while(option != 0);

        return false;

    }
}