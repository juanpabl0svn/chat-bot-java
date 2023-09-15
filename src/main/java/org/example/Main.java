package org.example;

import java.util.ArrayList;
import java.util.Scanner;



public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int attemps = 0;
    static Database db = new Database();
    static User user = null;
    static Account account = null;
    static Main main = new Main();

    public void sayHi(){
        System.out.println("Hola, yo soy, tu asistente virtual, ya estas registrado en Nu Bank? \n1.Si\n2.No\nOtro.Salir\n");
    }

    public static void main(String[] args){
        int isRegistered;
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
        System.out.println("Digite una contraseña numerica de 4 digitos");
        AccountData.add(scanner.next());
        while (AccountData.get(1) == null && !AccountData.get(1).matches("[0-9]+") && AccountData.get(1).length() != 4){
            System.out.println("Contraseña debe ser de 4 digitos y solo numeros");
            AccountData.set(1,scanner.next());
        }
        account = db.createAccount(user.nit,AccountData.get(0),AccountData.get(1));
        menu();
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
        user = db.getUserById(account.owner);
        if (user == null) {
            System.out.println("Algo salió mal, usuario no encontado");
            return false;
        }
        menu();
        return true;
    }
    public void makePQR(){
        System.out.println("Ingrese su PQR");
        String context = scanner.nextLine();
        System.out.println("Lamentamos mucho que haya tenido este inconveniente, lo resolveremos y nos comunicaremos tan rapido como sea posible con usted");
        db.PQR(user.nit,context);
    }

    public void changePassword(){
        String newPassword;
        do{
            System.out.println("Escribe tu nueva contraseña (Recuerda que deben ser solo numeros y de 4 cifras");
            newPassword = scanner.next();
        }while(newPassword == null && !newPassword.matches("[0-9]+") && newPassword.length() != 4);
        db.changePassword(account.username,newPassword);
        System.out.println("Contraseña correctamente guardada");
    }

    public boolean deleteAccount(){
        int answer;
        do{
            System.out.println("¿Esta seguro que desea borrar su cuenta y toda su información\n1.Si\n2.No");
            answer = scanner.nextInt();
        }while(answer != 1 && answer != 2);
        if (answer == 1){
            db.deleteAccount(account.number);
            System.out.println("Cuenta correctamente borrada");
            return true;
        }
        System.out.println("Proceso interrumpido");
        return false;
    }

        public boolean menu(){

        int option;

        do{
            System.out.println("Bienvenido "+user.name+ " " + user.surname+ ", estoy" +
                    " aqui para resolver tu dudas y inquietudes, " +
                    "selecciona una de las siguientes opciones:\n" +
                    "0.Salir\n"+
                    "1.Saldo en mi cuenta\n"+
                    "2.Saldo pendiente\n"+
                    "3.Realizar PQR\n"+
                    "4.Cambiar contraseña"+
                    "5.Cerrar cuenta\n");
            option = scanner.nextInt();
            if (option == 0) break;

            switch (option) {
                case 1 -> account.getBalance();
                case 2 -> account.getDebt();
                case 3 -> makePQR();
                case 4 -> changePassword();
                case 5 -> deleteAccount();
                default -> {}
            }

            System.out.println("Hay algo mas que pueda hacer por ti?\n1.Si\nOtro.No\n");

            option = scanner.nextInt();

            if (option != 1)break;


        }while(true);

        return false;

    }
}