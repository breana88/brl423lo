import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class AlsetMethods {

    public void logOn(String userID, String password){

        try (
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241",
            userID, password);
            Statement s=con.createStatement();) {
        }catch (Exception sqle){
            System.out.println(sqle.toString());
            Scanner myScanner = new Scanner(System.in);
            String id;
            String pass;
            System.out.print("connect error. Re-enter login data:");
            System.out.print("enter Oracle user id: ");
            id = myScanner.next();
            if (id.equals("")){
                System.exit(0);
            }
            System.out.print("enter Oracle password for " + id + ": ");
            pass = myScanner.next();
            logOn(id, pass);
        }
    }
//----------------------------------------------------------------------------------------------------------------
    public void viewCustomer(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                Scanner myScanner = new Scanner(System.in);
                System.out.print("Input the email affiliated to your account: ");
                String emailForSearch = myScanner.next();
                p = "select firstname, lastname, email, address from customer where email like '%" + emailForSearch + "%'";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println(answer.getString("firstname") + " " + answer.getString("lastname") 
                    + " \n" + answer.getString("email") + " \n" + answer.getString("address") + " \n" 
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void viewAllCustomer(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                p = "select * from customer";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Customer ID:  " + answer.getString("customer_ID") + " \n" + answer.getString("firstname") + " " + answer.getString("lastname") 
                    + " \n" + answer.getString("email") + " \n" + answer.getString("address") + " \nCardNumber: " + answer.getString("card_number_fk")
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void editCustomer(){
        int run1 = 0;
        String p = "";
        ResultSet answer;
        Scanner myScanner = new Scanner(System.in);
        do{
            //user menu here
            System.out.println("What aspect of your profile would you like to update?\n" 
                 + "1: first name \n"
                 + "2: last name \n"
                 + "3: email \n"
                 + "4: address \n"
                 + "5: Exit \n" );
            while (!myScanner.hasNextInt()) {
                String input = myScanner.next();
                System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-5*");
            }
            int c1 = myScanner.nextInt();
            if(c1 == 5){
                run1 = 1;
                break;
            }
            switch(c1){
                case 1: //first name
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED first name: ");
                        String newFN = myScanner.next();
                        p = "update customer set firstname = '%" + newFN + "%' where email = '%" + currE + "%'";
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println(answer.getString("firstname") + " " + answer.getString("lastname") 
                            + " \n" + answer.getString("email") + " \n" + answer.getString("address") + " \n" 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("First name updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //last name
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED last name: ");
                        String newLN = myScanner.next();
                        p = "update customer set lastname = '%" + newLN + "%' where email = '%" + currE + "%'";
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println(answer.getString("firstname") + " " + answer.getString("lastname") 
                            + " \n" + answer.getString("email") + " \n" + answer.getString("address") + " \n" 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Last name updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 3: //email
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED email: ");
                        String newE = myScanner.next();
                        p = "update customer set email = '%" + newE + "%' where email = '%" + currE + "%'";
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println(answer.getString("firstname") + " " + answer.getString("lastname") 
                            + " \n" + answer.getString("email") + " \n" + answer.getString("address") + " \n" 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Email updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 4: //address
                    try (
                            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                            Statement s=con.createStatement();
                        ) {
                            System.out.print("Input the email affiliated to your account: ");
                            String currE = myScanner.next();
                            System.out.print("Input the UPDATED address: ");
                            String newA = myScanner.next();
                            p = "update customer set address = '%" + newA + "%' where email = '%" + currE + "%'";
            
                            answer = s.executeQuery(p);
                            System.out.println();
            
                            while(answer.next()){
                                System.out.println(answer.getString("firstname") + " " + answer.getString("lastname") 
                                + " \n" + answer.getString("email") + " \n" + answer.getString("address") + " \n" 
                                + "---------------------------------------"); // FINISH
                            }

                            System.out.println("Address updated!");
                            s.close();
                            con.close();
                        }
                        catch (Exception sqle){
                            System.out.println(sqle.toString());
                        }
                break;
            }
        }while(run1 == 0);
    }
//----------------------------------------------------------------------------------------------------------------
    public void viewCard(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                Scanner myScanner = new Scanner(System.in);
                System.out.print("Input the email affiliated to your account: ");
                String emailForSearch = myScanner.next();
                p = "select * from card where card_number in (select card_number_fk from customer where email = '%" + emailForSearch + "%'";
  
                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println( "Card number: " + answer.getString("card_number") + " \nExpiration: " + answer.getString("expiration") 
                    + " \nZip: " + answer.getString("zip") + " \nSecuirty Code: " + answer.getString("security_code") + " \n" 
                    + "---------------------------------------"); // FINISH
                }
                s.close();  
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void viewAllCard(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                p = "select * from card";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println( "Card number: " + answer.getString("card_number") + " \nExpiration: " + answer.getString("expiration") 
                    + " \nZip: " + answer.getString("zip") + " \nSecuirty Code: " + answer.getString("security_code") + " \n" 
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void editCard(){
        int run1 = 0;
        String p = "";
        ResultSet answer;
        Scanner myScanner = new Scanner(System.in);
        do{
            //user menu here
            System.out.println("What aspect of your card would you like to update?\n" 
                 + "1: card number \n"
                 + "2: expiration \n"
                 + "3: security code \n"
                 + "4: zip \n"
                 + "5: Exit \n" );
            while (!myScanner.hasNextInt()) {
                String input = myScanner.next();
                System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-5*");
            }
            int c1 = myScanner.nextInt();
            if(c1 == 5){
                run1 = 1;
                break;
            }
            switch(c1){
                case 1: //card num
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED card number: ");
                        String newCN = myScanner.next();
                        p = "update card set card_number = " + newCN + " where in card_number = (select card_number_fk from customer where email = '%" + currE + "%'";
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Card number: " + answer.getString("card_number") + " \nExpiration: " + answer.getString("expiration") 
                            + " \nZip: " + answer.getString("zip") + " \nSecuirty Code: " + answer.getString("security_code") + " \n" 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Card Number updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //expiration
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED expiration date: ");
                        String newEX = myScanner.next();
                        p = "update card set expiration = " + newEX + " where in card_number = (select card_number_fk from customer where email = '%" + currE + "%";
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Card number: " + answer.getString("card_number") + " \nExpiration: " + answer.getString("expiration") 
                            + " \nZip: " + answer.getString("zip") + " \nSecuirty Code: " + answer.getString("security_code") + " \n" 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Expiration date updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 3: //security code
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED security code: ");
                        String newSC = myScanner.next();
                        p = "update card set security_code = " + newSC + " where in card_number = (select card_number_fk from customer where email = '%" + currE + "%";
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Card number: " + answer.getString("card_number") + " \nExpiration: " + answer.getString("expiration") 
                            + " \nZip: " + answer.getString("zip") + " \nSecuirty Code: " + answer.getString("security_code") + " \n" 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Security Code updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 4: //zip
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED zip code: ");
                        String newZC = myScanner.next();
                        p = "update card set zip = " + newZC + " where in card_number = (select card_number_fk from customer where email = '%" + currE + "%";
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Card number: " + answer.getString("card_number") + " \nExpiration: " + answer.getString("expiration") 
                            + " \nZip: " + answer.getString("zip") + " \nSecuirty Code: " + answer.getString("security_code") + " \n" 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Zip code updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
            }
        }while(run1 == 0);
    }
//----------------------------------------------------------------------------------------------------------------
    public void viewVehicle(){
    }
    public void viewAllVehicle(){

    }
    public void editVehicle(){

    }
//----------------------------------------------------------------------------------------------------------------
    public void viewModel(){

    }
    public void viewAllModel(){

    }
    public void editModel(){

    }
//----------------------------------------------------------------------------------------------------------------
    public void viewAllServiceLocations(){

    }
    public void editServiceLocations(){

    }
//----------------------------------------------------------------------------------------------------------------
    public void viewAllShowroom(){

    }
    public void editShowroom(){

    }
//----------------------------------------------------------------------------------------------------------------
    public void viewPrice(){

    }
    public void viewAllPrice(){

    }
    public void editPrice(){

    }
//----------------------------------------------------------------------------------------------------------------
    public void viewRepairs(){

    }
    public void viewAllRepairs(){

    }
    public void editRepairs(){

    }
}
