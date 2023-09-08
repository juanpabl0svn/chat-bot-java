package org.example;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.NumberFormat;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int attemps = 0;
    static Database db = new Database();
    static User user = null;
    static Account account = null;
    static Main main = new Main();
    static NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();

    public void sayHi(){
        System.out.println("Hola, yo soy, tu asistente virtual, ya estas registrado en Nu Bank? \n1.Si\n2.No\nOtro.Salir\n");
    }

    public static void main(String[] args) throws SQLException {
        int isRegistered;
        boolean succesfulyCreated;

        main.sayHi();

        isRegistered  = scanner.nextInt();

        switch (isRegistered) {
            case 1 -> main.logIn();
            case 2 -> main.signIn();
            default -> {}
        }
        main.sayBye();
    }

    public void sayBye(){
        System.out.println("\nGracias por usar nuestro servicio de asistente virtual, nos vemos pronto!!");
    }

    public boolean signIn(){
        ArrayList<String> PersonalData = new ArrayList<>();
        ArrayList<String> AccountData = new ArrayList<>();
        String[] questionsPersonalData = {"numero de identificación","primer nombre", "primer apellido","correo electronico"};
        String tries;

        for(String question: questionsPersonalData){
            System.out.println("Ingrese su " + question);
            PersonalData.add(scanner.next());
        }

        user = db.createUser(PersonalData.get(0),PersonalData.get(1),PersonalData.get(2),PersonalData.get(3));
        //user = new User(PersonalData.get(0),PersonalData.get(1),PersonalData.get(2),PersonalData.get(3));

        System.out.println(user.name);

        if (user == null){
            System.out.println("Algo salió mal, ¿Desea intentarlo de nuevo? \n1.Si\nOtro.No");
            tries = scanner.next();
            if (tries.equals("1")) {main.signIn();}
            return false;
        }

        System.out.println("Usuario correctamente creado, ahora digite la siguiente información para la creación de su cuenta\nIngrese un nombre de usuario");
        AccountData.add(scanner.next());
        while (db.usernameExist(AccountData.get(0))){
            System.out.println("Usuario ya en uso, digite uno nuevo");
            AccountData.set(0,scanner.next());
        }
        do{
            System.out.println("Digite una contraseña numerica de 4 digitos");
            AccountData.add(scanner.next());
        }while (AccountData.get(1) != null && AccountData.get(1).matches("[0-9]+") && AccountData.get(1).length() == 4);

        account = db.createAccount(PersonalData.get(0),AccountData.get(0),AccountData.get(1));

        main.menu();
        return true;
    }


    public boolean logIn() {
        String[] questionsLogIn = {"su usuario", "su contraseña"};
        ArrayList<String> data = new ArrayList<>();
        String repeatLogIn;

        for (String question: questionsLogIn){
            System.out.println("Ingrese por favor " + question);
            data.add(scanner.next());
        }


        //user = db.getUserById(nit);
        account = db.getAccount(data.get(0),data.get(1));

        if (account == null){
            if ( attemps >2){
                System.out.println("Numero maximo de intentos!");
                return false;
            }

            System.out.println("Usuario no encontrado, ¿Desea intentarlo de nuevo?");
            repeatLogIn = scanner.next();
            if (repeatLogIn.equals("1")){
                attemps++;
                return logIn();
            }
        }

        return true;


    }

    /*
    public boolean createAccount(){

    }

     */


    public boolean menu(){

        int option;

        float currency;

        do{
            System.out.println("Bienvenido "+user.name+ " " + user.surname+ ", estoy" +
                    " aqui para resolver tu dudas y inquietudes, " +
                    "selecciona una de las siguientes opciones:\n" +
                    "0.Salir\n" +
                    "1.Saldo en mi cuenta\n" +
                    "2.Saldo en pendiente\n" +
                    "3.Abonar dinero al credito\n" +

                    "4.Eliminar cuenta\n" +
                    "5.Conocer creditos\n");
            option = scanner.nextInt();

            if (option == 0) {
                break;
            }
            else if (option == 1){
                currency  = db.getBalance(user.nit);
                System.out.println("Tu saldo actual es de "+currencyInstance.format(currency));
            }
            else if(option == 2){
                currency  = db.getDebt(user.nit);
                System.out.println("Tu credito esta en "+currencyInstance.format(currency));
            }
            else if (option == 3){
                System.out.println("¿Cuanto desea abonar?");
            }

            System.out.println("Hay algo mas que pueda hacer por ti?\n" +
                    "1.Si\n" +
                    "Otro.No\n");

            option = scanner.nextInt();

            if (option != 1)break;


        }while(true);

        return false;

    }
}