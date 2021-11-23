import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class AlsetEnterpriseMain {
    public static void main (String[] args) throws SQLException, IOException, java.lang.ClassNotFoundException {
        int run = 0;
        int run1 = 0;
        int run2 = 0;
        int run3 = 0;
        int choice = -1;
        String userID;
        String password;
        AlsetMethods a = new AlsetMethods();
        Scanner s = new Scanner(System.in);

        do{
            System.out.println("Which user type are you?\n" 
                             + "1: Valued Alset Customer \n" 
                             + "2: Alset Store Manager \n" 
                             + "3: Alster Product Manager \n"
                             + "4: Exit Program");
            while (!s.hasNextInt()) {
                String input = s.next();
                System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-4*");
            }
            choice = s.nextInt();
            if(choice == 4){
                run = 1;
                break;
            }

            switch(choice){
                case 1: 
                    //log in method
                    System.out.print("enter Oracle user id: ");
                    userID = s.next();
                    System.out.print("enter Oracle password for " + userID + ": ");
                    password = s.next();
                    a.logOn(userID, password); //user is stuck in log on until log on is complete
                    //when user is logged in, they can view the menu and make actions
                    do{
                        //user menu here
                        System.out.println("Hello! What can we help you with?\n" 
                             + "1: Valued Alset Customer \n" );
                        while (!s.hasNextInt()) {
                            String input = s.next();
                            System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-4*");
                        }
                        int c1 = s.nextInt();
                        if(c1 == 4){
                            run1 = 1;
                            break;
                        }
                        switch(c1){
                            case 1:
                            break;
                        }
                    }while(run1 == 0);
                    break;
                case 2: //store manager menu here
                    System.out.println("manager");
                    break;
                case 3: //product manager menu here
                    System.out.println("product manager");
                    break;
            }


        }while (run == 0);

    }
}
