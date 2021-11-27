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
            myScanner.close();
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
                myScanner.close();
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
                myScanner.close();
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
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                Scanner myScanner = new Scanner(System.in);
                System.out.print("Input the email affiliated to your account: ");
                String emailForSearch = myScanner.next();
                p = "select * from vehicle where in customer_id_fk = (select customer_id from customer where email = '%" + emailForSearch + "%";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Vehicle ID: " + answer.getString("vehicle_id") + " \nVehicle sales: " + answer.getString("sales") 
                    + " \nModel ID: " + answer.getString("model_id_fk") + " \nMaintenance Status: " + answer.getString("maintenance") 
                    + " \nRecall status: " + answer.getString("recall_status")
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void viewAllVehicle(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                p = "select * from vehicle";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Vehicle ID: " + answer.getString("vehicle_id") + " \nVehicle sales: " + answer.getString("sales") 
                    + " \nModel ID: " + answer.getString("model_id_fk") + " \nMaintenance Status: " + answer.getString("maintenance") 
                    + " \nRecall status: " + answer.getString("recall_status") + " \nCustomer ID: " + answer.getString("customer_id_fk")
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void editVehicle(){
        int run1 = 0;
        String p = "";
        ResultSet answer;
        Scanner myScanner = new Scanner(System.in);
        do{
            //user menu here
            System.out.println("What aspect of a vehicle would you like to update?\n" 
                 + "1: recall status \n"
                 + "2: maintenance \n"
                 + "3: sales \n"
                 + "4: Exit \n" );
            while (!myScanner.hasNextInt()) {
                String input = myScanner.next();
                System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-4*");
            }
            int c1 = myScanner.nextInt();
            if(c1 == 4){
                run1 = 1;
                break;
            }
            switch(c1){
                case 1: //recall status
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the vehicle ID: ");
                        String vID = myScanner.next();
                        System.out.print("Input the UPDATED recall status: ");
                        String newRS = myScanner.next();
                        p = "update vehicle set recall_status = '%" + newRS + "%' where vehicle_id = " + vID;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Card number: " + answer.getString("card_number") + " \nExpiration: " + answer.getString("expiration") 
                            + " \nZip: " + answer.getString("zip") + " \nSecuirty Code: " + answer.getString("security_code") + " \n" 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Vehicle ID updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //maintenance
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the vehicle ID: ");
                        String vID = myScanner.next();
                        System.out.print("Input the UPDATED maintenance status: ");
                        String newM = myScanner.next();
                        p = "update vehicle set maintenance = '%" +newM + "%' where vehicle_id = " + vID ;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Card number: " + answer.getString("card_number") + " \nExpiration: " + answer.getString("expiration") 
                            + " \nZip: " + answer.getString("zip") + " \nSecuirty Code: " + answer.getString("security_code") + " \n" 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Maintenance updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 3: //sales
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the vehicle ID: ");
                        String vID = myScanner.next();
                        System.out.print("Input the UPDATED sales: ");
                        String newS = myScanner.next();
                        p = "update vehicle set sales = '%" + newS + "%' where vehicle_id = " + vID ;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Card number: " + answer.getString("card_number") + " \nExpiration: " + answer.getString("expiration") 
                            + " \nZip: " + answer.getString("zip") + " \nSecuirty Code: " + answer.getString("security_code") + " \n" 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Sales updated!");
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
    public void viewModel(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                Scanner myScanner = new Scanner(System.in);
                System.out.print("Input the model ID: ");
                String mID = myScanner.next();
                p = "select * from vehicle where model_id = " + mID;   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Model ID: " + answer.getString("model_id") + " \nModel description: " + answer.getString("model_desc") 
                    + " \nTerrain: " + answer.getString("terrain") + " \nAvailable Options: " + answer.getString("options") 
                    + " \nYear: " + answer.getString("year_model")
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void viewAllModel(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                p = "select * from vehicle_model";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Model ID: " + answer.getString("model_id") + " \nModel description: " + answer.getString("model_desc") 
                    + " \nTerrain: " + answer.getString("terrain") + " \nAvailable Options: " + answer.getString("options") 
                    + " \nYear: " + answer.getString("year_model")
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }

    }
    public void editModel(){
        int run1 = 0;
        String p = "";
        ResultSet answer;
        Scanner myScanner = new Scanner(System.in);
        do{
            //user menu here
            System.out.println("What aspect of a model would you like to update?\n" 
                 + "1: model description \n"
                 + "2: terrain \n"
                 + "3: options \n"
                 + "4: year \n"
                 + "5: exit" );
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
                case 1: //model description
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the model ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED model description: ");
                        String newD = myScanner.next();
                        p = "update vehicle_model set model_desc = '%" + newD + "%' where model_id = " + mID ;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println("Model ID: " + answer.getString("model_id") + " \nModel description: " + answer.getString("model_desc") 
                            + " \nTerrain: " + answer.getString("terrain") + " \nAvailable Options: " + answer.getString("options") 
                            + " \nYear: " + answer.getString("year_model")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Model description updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //terrain
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the model ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED terrain: ");
                        String newT = myScanner.next();
                        p = "update vehicle_model set terrain = '%" + newT + "%' where model_id = " + mID ;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println("Model ID: " + answer.getString("model_id") + " \nModel description: " + answer.getString("model_desc") 
                            + " \nTerrain: " + answer.getString("terrain") + " \nAvailable Options: " + answer.getString("options") 
                            + " \nYear: " + answer.getString("year_model")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Terrain updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 3: //options
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the model ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED available options: ");
                        String newD = myScanner.next();
                        p = "update vehicle_model set options = '%" + newD + "%' where model_id = '%" + mID + "%'";
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println("Model ID: " + answer.getString("model_id") + " \nModel description: " + answer.getString("model_desc") 
                            + " \nTerrain: " + answer.getString("terrain") + " \nAvailable Options: " + answer.getString("options") 
                            + " \nYear: " + answer.getString("year_model")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("options updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 4: //year
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the model ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED year: ");
                        String newY = myScanner.next();
                        p = "update vehicle_model set year = '%" + newY + "%' where model_id = " + mID;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println("Model ID: " + answer.getString("model_id") + " \nModel description: " + answer.getString("model_desc") 
                            + " \nTerrain: " + answer.getString("terrain") + " \nAvailable Options: " + answer.getString("options") 
                            + " \nYear: " + answer.getString("year_model")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Year updated!");
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
    public void viewAllServiceLocations(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                p = "select * from service_location";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Location ID: " + answer.getString("location_id") + " \nModels handled: " + answer.getString("models_handled") 
                    + " \nRecalls: " + answer.getString("recalls") + " \n Repairs offered: " + answer.getString("repairs_offered") 
                    + " \n For delivery: " + answer.getString("models_for_delivery")
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }

    }
    public void editServiceLocations(){
        int run1 = 0;
        String p = "";
        ResultSet answer;
        Scanner myScanner = new Scanner(System.in);
        do{
            //user menu here
            System.out.println("What aspect of a service location would you like to update?\n" 
                 + "1: models handled \n"
                 + "2: recalls \n"
                 + "3: repairs offered \n"
                 + "4: vehicles for delivery\n"
                 + "5: exit" );
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
                case 1: //models handled
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED list of models handled: ");
                        String newMH = myScanner.next();
                        p = "update service_location set models_handled = '%" + newMH + "%' where location_id = " + mID ;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println("Location ID: " + answer.getString("location_id") + " \nModels handled: " + answer.getString("models_handled") 
                            + " \nRecalls: " + answer.getString("recalls") + " \nRepairs offered: " + answer.getString("repairs_offered") 
                            + " \nVehicles for delivery: " + answer.getString("vehicles_for_delivery")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Models handled updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //recalls
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED recalls: ");
                        String newR = myScanner.next();
                        p = "update service_location set recalls = '%" + newR + "%' where location_id = " + mID;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println("Location ID: " + answer.getString("location_id") + " \nModels handled: " + answer.getString("models_handled") 
                            + " \nRecalls: " + answer.getString("recalls") + " \nRepairs offered: " + answer.getString("repairs_offered") 
                            + " \nVehicles for delivery: " + answer.getString("vehicles_for_delivery")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Recalls updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 3: //repairs offered
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED repairs available: ");
                        String newD = myScanner.next();
                        p = "update service_location set repairs_offered = '%" + newD + "%' where model_id = " + mID ;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println("Location ID: " + answer.getString("location_id") + " \nModels handled: " + answer.getString("models_handled") 
                            + " \nRecalls: " + answer.getString("recalls") + " \nRepairs offered: " + answer.getString("repairs_offered") 
                            + " \nVehicles for delivery: " + answer.getString("vehicles_for_delivery")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Repairs offered updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 4: //vehicles for delivery
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED vehicles for delivery: ");
                        String newR = myScanner.next();
                        p = "update service_location set vehicles_for_delivery = '%" + newR + "%' where location_id = " + mID;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println("Location ID: " + answer.getString("location_id") + " \nModels handled: " + answer.getString("models_handled") 
                            + " \nRecalls: " + answer.getString("recalls") + " \nRepairs offered: " + answer.getString("repairs_offered") 
                            + " \nVehicles for delivery: " + answer.getString("vehicles_for_delivery")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Vehicles for delivery updated!");
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
    public void viewAllShowroom(){

        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                p = "select * from service_location";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Location ID: " + answer.getString("location_id_fk") + " \nDisplay Vehicles: " + answer.getString("display_vehicles") 
                    + " \nUsed Vehicles: " + answer.getString("used_vehicles") 
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void editShowroom(){
        int run1 = 0;
        String p = "";
        ResultSet answer;
        Scanner myScanner = new Scanner(System.in);
        do{
            //user menu here
            System.out.println("What aspect of a showroom would you like to update?\n" 
                 + "1: update used vehicles\n"
                 + "2: update display vehicles\n"
                 + "3: exit" );
            while (!myScanner.hasNextInt()) {
                String input = myScanner.next();
                System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-3*");
            }
            int c1 = myScanner.nextInt();
            if(c1 == 3){
                run1 = 1;
                break;
            }
            switch(c1){
                case 1: //vehicle status
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED list of used vehicles: ");
                        String newMH = myScanner.next();
                        p = "update showroom set used_vehicles = '%" + newMH + "%' where location_id_fk = " + mID ;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println("Location ID: " + answer.getString("location_id_fk") + " \nUsed vehicles: " + answer.getString("used_vehicles") 
                            + " \nDisplay vehicles: " + answer.getString("display_vehicles")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("used vehicles updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //recalls
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED list of display vehicles: ");
                        String newMH = myScanner.next();
                        p = "update showroom set display_vehicles = '%" + newMH + "%' where location_id_fk = " + mID ;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println("Location ID: " + answer.getString("location_id_fk") + " \nUsed vehicles: " + answer.getString("used_vehicles") 
                            + " \nDisplay vehicles: " + answer.getString("display_vehicles")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("display vehicles updated!");
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
    public void viewPrice(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                Scanner myScanner = new Scanner(System.in);
                System.out.print("Input the item: ");
                String item = myScanner.next();
                p = "select * from price where item = '%" + item + "%'";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println( "Price ID: " + answer.getString("price_id") + " \nItem: " + answer.getString("item") 
                    + " \nPrice: " + answer.getString("price") + " \nAvailable for discount: " + answer.getString("discount") + " \nAvailable for bundle: " + answer.getString("available_for_bundle") 
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void viewAllPrice(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                p = "select * from prices";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println( "Price ID: " + answer.getString("price_id") + " \nItem: " + answer.getString("item") 
                    + " \nPrice: " + answer.getString("price") + " \nAvailable for discount: " + answer.getString("discount") + " \nAvailable for bundle: " + answer.getString("available_for_bundle") 
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void editPrice(){
        int run1 = 0;
        String p = "";
        ResultSet answer;
        Scanner myScanner = new Scanner(System.in);
        do{
            //user menu here
            System.out.println("What aspect of your card would you like to update?\n" 
                 + "1: price \n"
                 + "2: availibility for discount \n"
                 + "3: availibility for bundle \n"
                 + "4: Exit \n" );
            while (!myScanner.hasNextInt()) {
                String input = myScanner.next();
                System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-4*");
            }
            int c1 = myScanner.nextInt();
            if(c1 == 4){
                run1 = 1;
                break;
            }
            switch(c1){
                case 1: //price
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input price id: ");
                        String pID = myScanner.next();
                        System.out.print("Input the UPDATED price: ");
                        String newP = myScanner.next();
                        p = "update prices set price = " + newP + " where in price_id = " + pID;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Price ID: " + answer.getString("price_id") + " \nItem: " + answer.getString("item") 
                            + " \nPrice: " + answer.getString("price") + " \nAvailable for discount: " + answer.getString("discount") + " \nAvailable for bundle: " + answer.getString("available_for_bundle") 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Price updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //discount
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input price id: ");
                        String pID = myScanner.next();
                        System.out.print("Input the UPDATED discount status: ");
                        String newP = myScanner.next();
                        p = "update prices set discount = " + newP + " where in price_id = " + pID;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Price ID: " + answer.getString("price_id") + " \nItem: " + answer.getString("item") 
                            + " \nPrice: " + answer.getString("price") + " \nAvailable for discount: " + answer.getString("discount") + " \nAvailable for bundle: " + answer.getString("available_for_bundle") 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("discount status updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 3: //bundle
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input price id: ");
                        String pID = myScanner.next();
                        System.out.print("Input the UPDATED bundle status: ");
                        String newP = myScanner.next();
                        p = "update prices set available_for_bundle = " + newP + " where in price_id = " + pID;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Price ID: " + answer.getString("price_id") + " \nItem: " + answer.getString("item") 
                            + " \nPrice: " + answer.getString("price") + " \nAvailable for discount: " + answer.getString("discount") + " \nAvailable for bundle: " + answer.getString("available_for_bundle") 
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Bundle status updated!");
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
    public void viewRepairs(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                Scanner myScanner = new Scanner(System.in);
                System.out.print("Input the repair number: ");
                String RN = myScanner.next();
                p = "select * from repairs where repair_number = " + RN;   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println( "Repair number: " + answer.getString("repair_number") + " \nTotal cost: " + answer.getString("total_cost") 
                    + " \nVehicle ID: " + answer.getString("vehicle_id_fk") + " \nLocation ID: " + answer.getString("location_id") + " \nDate of repair: " + answer.getString("date_of_repair") 
                    + " \nPrice ID: " + answer.getString("price_id_fk")
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void viewAllRepairs(){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                Statement s=con.createStatement();
            ) {
                p = "select * from repairs";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println( "Repair number: " + answer.getString("repair_number") + " \nTotal cost: " + answer.getString("total_cost") 
                    + " \nVehicle ID: " + answer.getString("vehicle_id_fk") + " \nLocation ID: " + answer.getString("location_id") + " \nDate of repair: " + answer.getString("date_of_repair") 
                    + " \nPrice ID: " + answer.getString("price_id_fk")
                    + "---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void editRepairs(){
        int run1 = 0;
        String p = "";
        ResultSet answer;
        Scanner myScanner = new Scanner(System.in);
        do{
            //user menu here
            System.out.println("What aspect of your card would you like to update?\n" 
                 + "1: total cost \n"
                 + "2: date of service \n"
                 + "3: Exit \n" );
            while (!myScanner.hasNextInt()) {
                String input = myScanner.next();
                System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-3*");
            }
            int c1 = myScanner.nextInt();
            if(c1 == 3){
                run1 = 1;
                break;
            }
            switch(c1){
                case 1: //total 
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input repair number: ");
                        String RN = myScanner.next();
                        System.out.print("Input the UPDATED total: ");
                        String newT = myScanner.next();
                        p = "update repairs set total_cost = " + newT + " where in repair_number = " + RN;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Repair number: " + answer.getString("repair_number") + " \nTotal cost: " + answer.getString("total_cost") 
                            + " \nVehicle ID: " + answer.getString("vehicle_id_fk") + " \nLocation ID: " + answer.getString("location_id") + " \nDate of repair: " + answer.getString("date_of_repair") 
                            + " \nPrice ID: " + answer.getString("price_id_fk")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("total cost updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //date of service
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241");
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input repair number: ");
                        String RN = myScanner.next();
                        System.out.print("Input the UPDATED date of service: ");
                        String newT = myScanner.next();
                        p = "update repairs set date_of_repair = " + newT + " where in repair_number = " + RN;
        
                        answer = s.executeQuery(p);
                        System.out.println();
        
                        while(answer.next()){
                            System.out.println( "Repair number: " + answer.getString("repair_number") + " \nTotal cost: " + answer.getString("total_cost") 
                            + " \nVehicle ID: " + answer.getString("vehicle_id_fk") + " \nLocation ID: " + answer.getString("location_id") + " \nDate of repair: " + answer.getString("date_of_repair") 
                            + " \nPrice ID: " + answer.getString("price_id_fk")
                            + "---------------------------------------"); // FINISH
                        }

                        System.out.println("Date of service updated!");
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
}
