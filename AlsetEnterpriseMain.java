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
                             + "1: View profile \n"
                             + "2: Edit profile \n"
                             + "3: View wallet \n"
                             + "4: Edit wallet \n"
                             + "5: View your vehicles \n"
                             + "6: View service locations \n"
                             + "7: View all Alset models \n" //inside add option to see model options as well
                             + "8: Exit \n" );
                        while (!s.hasNextInt()) {
                            String input = s.next();
                            System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-8*");
                        }
                        int c1 = s.nextInt();
                        if(c1 == 8){
                            run1 = 1;
                            break;
                        }
                        switch(c1){
                            case 1:
                            break;
                            case 2:
                            break;
                            case 3:
                            break;
                            case 4:
                            break;
                            case 5:
                            break;
                            case 6:
                            break;
                            case 7:
                            break;
                        }
                    }while(run1 == 0);
                    break;
                case 2: 
                 //log in method
                 System.out.print("enter Oracle user id: ");
                 userID = s.next();
                 System.out.print("enter Oracle password for " + userID + ": ");
                 password = s.next();
                 a.logOn(userID, password); //user is stuck in log on until log on is complete
                 //when user is logged in, they can view the menu and make actions
                 do{
                    //store manager menu here
                     System.out.println("Hello Store Manager! What can we help you with?\n" 
                          + "1: View all customers \n" //view cards per customer inside 
                          + "3: View prices \n"
                          + "4: Edit prices \n" //edit prices if new repairs are added
                          + "5: View repairs \n"
                          + "6: Edit repairs \n" //managers can add aviable repairs, if they hire a new employee who can handle a new repair type
                          + "7: View all Alset models \n" //inside add option to see model options as well
                          + "8: View Showroom information \n"
                          + "9: Edit Showroom information \n"
                          + "10: View service location information \n"
                          + "11: Edit Showroom information \n" 
                          + "12: Exit \n" );
                     while (!s.hasNextInt()) {
                         String input = s.next();
                         System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-12*");
                     }
                     int c2 = s.nextInt();
                     if(c2 == 12){
                         run1 = 1;
                         break;
                     }
                     switch(c2){
                         case 1:
                         break;
                         case 2:
                         break;
                         case 3:
                         break;
                         case 4:
                         break;
                         case 5:
                         break;
                         case 6:
                         break;
                         case 7:
                         break;
                         case 8:
                         break;
                         case 9:
                         break;
                         case 10:
                         break;
                         case 11:
                         break;
                     }
                 }while(run2 == 0);
                    break;
                case 3: //product manager menu here
                    //log in method
                 System.out.print("enter Oracle user id: ");
                 userID = s.next();
                 System.out.print("enter Oracle password for " + userID + ": ");
                 password = s.next();
                 a.logOn(userID, password); //user is stuck in log on until log on is complete
                 //when user is logged in, they can view the menu and make actions
                 do{
                    //store manager menu here
                     System.out.println("Hello Product Manager! What can we help you with?\n" 
                          + "1: View all customers \n" //view cards per customer inside 
                          + "3: View prices \n"
                          + "4: Edit prices \n" //edit prices if products are changed
                          + "5: View repairs \n"
                          + "6: View all Alset models \n" //inside add option to see model options as well
                          + "7: Edit Alset models"
                          + "8: View Showroom information \n"
                          + "9: View service location information \n"
                          + "10: Issue maintenance \n"
                          + "11: Issue recall \n"
                          + "12: Exit \n" );
                     while (!s.hasNextInt()) {
                         String input = s.next();
                         System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-12*");
                     }
                     int c3 = s.nextInt();
                     if(c3 == 12){
                         run3 = 1;
                         break;
                     }
                     switch(c3){
                         case 1:
                         break;
                         case 2:
                         break;
                         case 3:
                         break;
                         case 4:
                         break;
                         case 5:
                         break;
                         case 6:
                         break;
                         case 7:
                         break;
                         case 8:
                         break;
                         case 9:
                         break;
                         case 10:
                         break;
                         case 11:
                         break;
                     }
                 }while(run3 == 0);
                    break;
            }
        }while (run == 0);

    }
}
