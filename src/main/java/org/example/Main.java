package org.example;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int attemps = 0;
    static Database db = new Database();
    static User user = null;
    static Main main = new Main();


    public static void main(String[] args) throws SQLException {
        int isRegistered;
        boolean succesfulyCreated;


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
            succesfulyCreated = main.signIn();
            if (succesfulyCreated){
                System.out.println("Usuario correctamente creado");
                main.menu();

            }else{
                succesfulyCreated = main.signIn();
                if (succesfulyCreated){
                    System.out.println("USario creado con exito !! \n");
                    main.menu();
                }else{
                    System.out.println("Algo salio mal, vuelve a intentarlo mas tarde");
                }

            }

        }
        main.sayBye();


    }

    public void sayBye(){
        System.out.println("\nGracias por usar nuestro servicio " +
                "de asistente virtual, nos vemos pronto!!");
    }

    public boolean signIn(){
        String nit,name, surname;

        System.out.println("Vamos a registrarte en el sistema:\n" +
                "Por favor ingresa tu numero de identificación");
        nit = scanner.next();
        System.out.println("Ingresa tu primer y segundo nombre si posees uno\n");
        name = scanner.next();
        System.out.println("Ingrese sus apellidos:\n");
        surname = scanner.next();

        user = db.createUser(nit,name,surname);

        System.out.println(user);
        System.out.println(user.name);

        return user != null;

    }

    public boolean logIn(){
        String nit;
        boolean loggedIn;
        int repeatLogIn;

        System.out.println("Ingrese por favor su numero de " +
                "cedula: \n");

        nit = scanner.next();

        user = db.getUserById(nit);

        loggedIn = user != null;

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

        do{
            System.out.println("Bienvenido "+user.name+ " " + user.surname+ ", estoy" +
                    " aqui para resolver tu dudas y inquietudes, " +
                    "selecciona una de las siguientes opciones:\n" +
                    "0.Salir\n" +
                    "1.Saldo pendiente\n" +
                    "2.Saldo en mi cuenta\n" +
                    "3.Cambiar información personal\n" +
                    "4.Eliminar cuenta\n" +
                    "5.Conocer creditos\n");
            option = scanner.nextInt();

            if (option == 0) {
                break;
            }
            else if (option == 1){
                db.getMyCurrency(user.nit);
            }

        }while(true);

        return false;

    }
}