import java.io.*;
import java.net.PasswordAuthentication;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;

public class AlsetMethods {

    public void logOn(String userID, String password){
        try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241",userID, password);
            Statement s=conn.createStatement();)
        {
           
        } catch (Exception sqle){
            System.out.println(sqle.toString() + "\n");
            Scanner myScanner = new Scanner(System.in);
            String id;
            String pass;
            System.out.print("connect error. Re-enter login data\n");
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
    public void viewCustomer(String userID, String password){ //good
        String p = "";
        ResultSet answer;

        try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241",userID, password);
                Statement s=con.createStatement();
            ){ 
                Scanner myScanner = new Scanner(System.in);
                System.out.print("Input the email affiliated to your account: ");
                String emailForSearch = myScanner.next();
                p = "select * from customer where email = \'" + emailForSearch + "\'";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("User ID: " + answer.getString("customer_id") + " \n" 
                    + answer.getString("firstname") + " " + answer.getString("lastname") + " \n" 
                    + answer.getString("email") + " \n" 
                    + answer.getString("address") + " \n" 
                    + "---------------------------------------"); // FINISH
                }
     
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void viewAllCustomer(String userID, String password){ //good
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                p = "select * from customer";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Customer ID:  " + answer.getString("customer_ID") + " \n" + answer.getString("firstname") + " " + answer.getString("lastname") 
                    + " \n" + answer.getString("email") + " \n" + answer.getString("address") + " \nCardNumber: " + answer.getString("card_number_fk")
                    + "\n---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void editCustomer(String userID, String password){ //good
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
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED first name: ");
                        String newFN = myScanner.next();
                    
                        s.executeUpdate("update customer set firstname = \'" + newFN + "\' where email = \'" + currE + "\'");
                        System.out.println();
                        System.out.println("First name updated");
                        
                        
                        s.close();
                        con.close();
                         /**
                          * System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED card number: ");
                        String newCN = myScanner.next();
                        //p = "update card set card_number = " + newCN + " where card_number = (select card_number_fk from customer where email = \'" + currE  + "\')";
                        s.executeUpdate("update card set card_number = " + newCN + " where card_number = (select card_number_fk from customer where email = \'" + currE  + "\')");
                       
                        System.out.println();
                        System.out.println("Card Number updated!");
                        s.close();
                        con.close();
                          */
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //last name
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241",userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED last name: ");
                        String newLN = myScanner.next();
                        s.executeUpdate("update customer set lastname = \'" + newLN + "\' where email = \'" + currE + "\'");
                        //p = "update customer set lastname = \'" + newLN + "\' where email = \'" + currE + "\'";
        
                        //answer = s.executeQuery(p);
                        System.out.println();
        

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
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED email: ");
                        String newE = myScanner.next();
                        s.executeUpdate("update customer set email = \'" + newE + "\' where email = \'" + currE + "\'");
                        //p = "update customer set email = \'" + newE + "\' where email = \'" + currE + "\'";
                        System.out.println();

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
                            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                            Statement s=con.createStatement();
                        ) {
                            System.out.print("Input the email affiliated to your account: ");
                            String currE = myScanner.next();
                            System.out.print("Input the UPDATED address: ");
                            String newA = myScanner.next();
                            s.executeUpdate("update customer set address = \'" + newA + "\' where email = \'" + currE + "\'");
                            //p = "update customer set address = \'" + newA + "\' where email = \'" + currE + "\'";
            
    
                            System.out.println();
            
                    

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
    public void viewCard(String userID, String password){ //good
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                Scanner myScanner = new Scanner(System.in);
                System.out.print("Input the email affiliated to your account: ");
                String emailForSearch = myScanner.next();
                p = "select * from card where card_number = (select card_number_fk from customer where email = \'" + emailForSearch + "\')";
  
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
    public void viewAllCard(String userID, String password){ //good
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
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
    public void editCard(String userID, String password){ //works
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
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED card number: ");
                        String newCN = myScanner.next();
                        //p = "update card set card_number = " + newCN + " where card_number = (select card_number_fk from customer where email = \'" + currE  + "\')";
                        s.executeUpdate("update card set card_number = " + newCN + " where card_number = (select card_number_fk from customer where email = \'" + currE  + "\')");
                       
                        System.out.println();
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
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED expiration date: ");
                        String newEX = myScanner.next();
                        s.executeUpdate("update card set expiration = " + newEX + " where card_number = (select card_number_fk from customer where email = \'" + currE  + "\')");
                        //p = "update card set expiration = " + newEX + " where card_number = (select card_number_fk from customer where email = \'" + currE  + "\')";
        
                        System.out.println();
        

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
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED security code: ");
                        String newSC = myScanner.next();
                        s.executeUpdate("update card set security_code = " + newSC + " where card_number = (select card_number_fk from customer where email = \'" + currE  + "\')");
                       // p = "update card set security_code = " + newSC + " where card_number = (select card_number_fk from customer where email = \'" + currE  + "\')";
        
                        System.out.println();

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
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the email affiliated to your account: ");
                        String currE = myScanner.next();
                        System.out.print("Input the UPDATED zip code: ");
                        String newZC = myScanner.next();
                        //p = "update card set zip = " + newZC + " where card_number = (select card_number_fk from customer where email = \'" + currE + "\')";
                        s.executeUpdate("update card set zip = " + newZC + " where card_number = (select card_number_fk from customer where email = \'" + currE + "\')");
                        System.out.println();

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
    public void viewVehicle (String userID, String password){ //good
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                Scanner myScanner = new Scanner(System.in);
                System.out.print("Input the email affiliated to your account: ");
                String emailForSearch = myScanner.next();
                p = "select * from vehicle where customer_id_fk = (select customer_id from customer where email = \'" + emailForSearch + "\')";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Vehicle ID: " + answer.getString("vehicle_id") + " \nVehicle sales: " + answer.getString("sales") 
                    + " \nModel ID: " + answer.getString("model_id_fk") + " \nMaintenance Status: " + answer.getString("maintenance") 
                    + " \nRecall status: " + answer.getString("recall_status")
                    + "\n---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void viewAllVehicle(String userID, String password){ //fine
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
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
    public void editVehicle(String userID, String password){ //fine
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
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the vehicle ID: ");
                        String vID = myScanner.next();
                        System.out.print("Input the UPDATED recall status: ");
                        String newRS = myScanner.next();
                        //p = "update vehicle set recall_status = \'" + newRS + "\' where vehicle_id = " + vID;
                        s.executeUpdate("update vehicle set recall_status = \'" + newRS + "\' where vehicle_id = \'" + vID + "\'");
                        
                        
                        System.out.println();

                        System.out.println("recall status updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //maintenance
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the vehicle ID: ");
                        String vID = myScanner.next();
                        System.out.print("Input the UPDATED maintenance status: ");
                        String newM = myScanner.next();
                        //p = "update vehicle set maintenance = \'" +newM + "\' where vehicle_id = " + vID ;
                        s.executeUpdate("update vehicle set maintenance = \'" +newM + "\' where vehicle_id = \'" + vID + "\'");
                       
                        System.out.println();

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
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the vehicle ID: ");
                        String vID = myScanner.next();
                        System.out.print("Input the UPDATED sales: ");
                        String newS = myScanner.next();
                        //p = "update vehicle set sales = \'" + newS + "\' where vehicle_id = \'" + vID + "\'" ;
                        s.executeUpdate("update vehicle set sales = \'" + newS + "\' where vehicle_id = \'" + vID + "\'");
                       
                        System.out.println();
        
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
    public void viewModel(String userID, String password){ //fine??
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
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
    public void viewAllModel(String userID, String password){ //good
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                p = "select * from vehicle_model";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Model ID: " + answer.getString("model_id") + " \nModel description: " + answer.getString("model_desc") 
                    + " \nTerrain: " + answer.getString("terrain") + " \nAvailable Options: " + answer.getString("options") 
                    + " \nYear: " + answer.getString("yearmodel") + "\nPrice ID: " + answer.getString("price_id_fk")
                    + "\n---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }

    }
    public void editModel(String userID, String password){ //year invalid identifier
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
                        //invalid sql statement
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the model ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED model description: ");
                        String newD = myScanner.next();
                        //p = "update vehicle_model set model_desc = \'" + newD + "\' where model_id = \'" + mID + "\'" ;
                        s.executeUpdate("update vehicle_model set model_desc = \'" + newD + "\' where model_id = \'" + mID + "\'");
                        //answer = s.executeQuery(p);
                        System.out.println();

                        System.out.println("Model description updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //terrain
                        //invalid sql statement
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the model ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED terrain: ");
                        String newT = myScanner.next();
                        //p = "update vehicle_model set terrain = \'" + newT + "\' where model_id = \'" + mID + "\'";
                        s.executeUpdate("update vehicle_model set terrain = \'" + newT + "\' where model_id = \'" + mID + "\'");
                        System.out.println();

                        System.out.println("Terrain updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 3: //options
                //invalid number
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the model ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED available options: ");
                        String newD = myScanner.next();
                        //p = "update vehicle_model set options = \'" + newD + "\' where model_id = \'" + mID + "\'";
                        s.executeUpdate("update vehicle_model set options = \'" + newD + "\' where model_id = \'" + mID + "\'");
                       // answer = s.executeQuery(p);
                        System.out.println();
        
                        System.out.println("options updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 4: //year
                    //year invalid identifier
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the model ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED year: ");
                        String newY = myScanner.next();
                        //p = "update vehicle_model set yearmodel = \'" + newY + "\' where model_id = \'" + mID + "\'";
                        s.executeUpdate("update vehicle_model set yearmodel = \'" + newY + "\' where model_id = \'" + mID + "\'");
                        //answer = s.executeQuery(p);
                        System.out.println();
    
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
    public void viewAllServiceLocations(String userID, String password){ //invalid colum name
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                p = "select * from service_location";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Location ID: " + answer.getString("location_id") + " \nModels handled: " + answer.getString("models_handled") 
                    + " \nRecalls: " + answer.getString("recalls") + " \nRepairs offered: " + answer.getString("repairs_offered") 
                    + " \nFor delivery: " + answer.getString("vehicles_for_delivery")
                    + "\n---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }

    }
    public void editServiceLocations(String userID, String password){ //good
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
                //invalid sql statment
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED list of models handled: ");
                        String newMH = myScanner.next();
                        //p = "update service_location set models_handled = \'" + newMH + "\' where location_id = \'" + mID + "\'";
                        s.executeUpdate("update service_location set models_handled = \'" + newMH + "\' where location_id = \'" + mID + "\'");
                        System.out.println();

                        System.out.println("Models handled updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //recalls
                //invalid sql statment
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED recalls: ");
                        String newR = myScanner.next();
                        //p = "update service_location set recalls = \'" + newR + "\' where location_id =  \'" + mID + "\'";
                        s.executeUpdate("update service_location set recalls = \'" + newR + "\' where location_id =  \'" + mID + "\'");
                        
                        System.out.println();

                        System.out.println("Recalls updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 3: //repairs offered
                //model_id invalid identifier
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED repairs available: ");
                        String newD = myScanner.next();
                        //p = "update service_location set repairs_offered = \'" + newD + "\' where model_id = \'" + mID + "\'" ;
                        s.executeUpdate("update service_location set repairs_offered = \'" + newD + "\' where model_id = \'" + mID + "\'");
                    
                        System.out.println();
    
                        System.out.println("Repairs offered updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 4: //vehicles for delivery
                //invalid sql statement
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED vehicles for delivery: ");
                        String newR = myScanner.next();
                        //p = "update service_location set vehicles_for_delivery = \'" + newR + "\' where location_id = \'" + mID + "\'";
                        s.executeUpdate("update service_location set vehicles_for_delivery = \'" + newR + "\' where location_id = \'" + mID + "\'");
                        System.out.println();
    

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
    public void viewAllShowroom(String userID, String password){ //invaalid column name

        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                p = "select * from showroom";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println("Location ID: " + answer.getString("location_id_fk") + " \nDisplay Vehicles: " + answer.getString("display_vehicles") 
                    + " \nUsed Vehicles: " + answer.getString("used_vehicles") 
                    + "\n---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void editShowroom(String userID, String password){ //good
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
                //stuck in loop
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED list of used vehicles: ");
                        String newMH = myScanner.next();
                        s.executeUpdate("update showroom set used_vehicles = \'" + newMH + "\' where location_id_fk = \'" + mID + "\'");
                        
                        System.out.println();
    

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
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input the location ID: ");
                        String mID = myScanner.next();
                        System.out.print("Input the UPDATED list of display vehicles: ");
                        String newMH = myScanner.next();
                        s.executeUpdate("update showroom set display_vehicles = \'" + newMH + "\' where location_id_fk = \'" + mID + "\'");
                        
                        System.out.println();
        

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
    public void viewPrice(String userID, String password){
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                Scanner myScanner = new Scanner(System.in);
                System.out.print("Input the item: ");
                String item = myScanner.next();
                p = "select * from prices where item = \'" + item + "\'";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println( "Price ID: " + answer.getString("price_id") + " \nItem: " + answer.getString("item") 
                    + " \nPrice: " + answer.getString("price") + " \nAvailable for discount: " + answer.getString("discounts") 
                    + " \nAvailable for bundle: " + answer.getString("available_for_package") 
                    + "\n---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void viewAllPrice(String userID, String password){ //good
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                p = "select * from prices";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println( "Price ID: " + answer.getString("price_id") + " \nItem: " + answer.getString("item") 
                    + " \nPrice: " + answer.getString("price") + " \nAvailable for discount: " + answer.getString("discounts") 
                    + " \nAvailable for bundle: " + answer.getString("available_for_package") 
                    + "\n---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void editPrice(String userID, String password){  //good

        int run1 = 0;
        String p = "";
        ResultSet answer;
        Scanner myScanner = new Scanner(System.in);
        do{
            //user menu here
            System.out.println("What aspect of your prices would you like to update?\n"  //change this question
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
                //invalid sql statment
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input price id: ");
                        String pID = myScanner.next();
                        System.out.print("Input the UPDATED price: ");
                        String newP = myScanner.next();
                        s.executeUpdate( "update prices set price = \'" + newP + "\' where price_id = \'" + pID + "\'");
                        System.out.println();
    
                        System.out.println("Price updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //discount
                //yes invalid identifier
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input price id: ");
                        String pID = myScanner.next();
                        System.out.print("Input the UPDATED discount status: ");
                        String newP = myScanner.next();
                        //p = "update prices set discount = \'" + newP + "\' where price_id = \'" + pID + "\'";
                        s.executeUpdate("update prices set discount = \'" + newP + "\' where price_id = \'" + pID + "\'");
                        System.out.println();

                        System.out.println("discount status updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 3: //bundle
                //yes invalid identifier
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input price id: ");
                        String pID = myScanner.next();
                        System.out.print("Input the UPDATED bundle status: ");
                        String newP = myScanner.next();
                        //p = "update prices set available_for_bundle = \'" + newP + "\' where price_id = \'" + pID + "\'";
                        s.executeUpdate("update prices set available_for_bundle = \'" + newP + "\' where price_id = \'" + pID + "\'");
                       
                        System.out.println();

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
    public void viewRepairs(String userID, String password){ //invaild col name
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                Scanner myScanner = new Scanner(System.in);
                System.out.print("Input the repair number: ");
                String RN = myScanner.next();
                p = "select * from repairs where repair_number = \'" + RN + "\'";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println( "Repair number: " + answer.getString("repair_number") 
                    + " \nTotal cost: " + answer.getString("total_cost") 
                    + " \nVehicle ID: " + answer.getString("vehicle_id_fk") 
                    + " \nLocation ID: " + answer.getString("location_id_fk")
                     + "\nDate of repair: " + answer.getString("date_of_repair") 
                    + " \nPrice ID: " + answer.getString("price_id_fk")
                    + "\n---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void viewAllRepairs(String userID, String password){ //invalid col name
        String p = "";
        ResultSet answer;
        
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                p = "select * from repairs";   

                answer = s.executeQuery(p);
                System.out.println();

                while(answer.next()){
                    System.out.println( "Repair number: " + answer.getString("repair_number") 
                    + " \nTotal cost: " + answer.getString("total_cost") 
                    + " \nVehicle ID: " + answer.getString("vehicle_id_fk") 
                    + " \nLocation ID: " + answer.getString("location_id_fk")
                     + "\nDate of repair: " + answer.getString("date_of_repair") 
                    + " \nPrice ID: " + answer.getString("price_id_fk")
                    + "\n---------------------------------------"); // FINISH
                }
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void editRepairs(String userID, String password){
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
                //invalid sql statement
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input repair number: ");
                        String RN = myScanner.next();
                        System.out.print("Input the UPDATED total: ");
                        String newT = myScanner.next();
                        s.executeUpdate("update repairs set total_cost = \'" + newT + "\' where repair_number = \'" + RN + "\'");
        
                        System.out.println();

                        System.out.println("total cost updated!");
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: //date of service
                //invaild sql satatment
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        System.out.print("Input repair number: ");
                        String RN = myScanner.next();
                        System.out.print("Input the UPDATED date of service: ");
                        String newT = myScanner.next();
                        s.executeUpdate("update repairs set date_of_repair = \'" + newT + "\' where repair_number = \'" + RN + "\'");
                      
                        System.out.println();
    
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
//----------------------------------------------------------------------------------------------------------------
    public void issue_recall(String userID, String password){ //sql command not properly used
        String p = "";
        String q = "";
        ResultSet answer;
        ResultSet answer2;
        Scanner myScanner = new Scanner(System.in);
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                System.out.print("Input the model ID for recall: ");
                String mID = myScanner.next();
                System.out.print("Input the year of model: ");
                String newM = myScanner.next();
                //p = "update vehicle set recall_status = \'" + 1 + "\' where \'" + newM + "\' = (select yearmodel from vehicle_model where model_id = \'" + mID + "\')";
                s.executeUpdate("update vehicle set recall_status = \'" + 1 + "\' where \'" + newM + "\' = (select yearmodel from vehicle_model where model_id = \'" + mID + "\')");
                System.out.println();
                
                q = "select email from customer where customer_id = (select customer_id_fk from vehicle where model_id_fk = \'" + mID + "\') ";
                answer2 = s.executeQuery(q);
                System.out.println();

                while(answer2.next()){
                    System.out.println("Email has been sent to " + answer2.getString("email") + "\n");
                }

                System.out.println("recall status updated!");
                s.close();
                con.close();
            }
            catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void issue_maintenance(String userID, String password){ //frozen
        String p = "";
        String q ="";
        ResultSet answer2;
        ResultSet answer;
        Scanner myScanner = new Scanner(System.in);
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                Statement s=con.createStatement();
            ) {
                System.out.print("Input the vehicle ID for maintenance: ");
                String vID = myScanner.next();
                
                s.executeUpdate("update vehicle set maintenance = \'" + 1 + "\' where vehicle_id = \'" + vID + "\'" );
      
                System.out.println();
                q = "select email from customer where customer_id = (select customer_id_fk from vehicle where vehicle_id = " + vID + ") ";
                answer2 = s.executeQuery(q);
                System.out.println();

                while(answer2.next()){
                    System.out.println("Email has been sent to " + answer2.getString("email") + "\n");
                }
                System.out.println("maintenance status updated!");
                s.close();
                con.close();
            }catch (Exception sqle){
                System.out.println(sqle.toString());
            }
    }
    public void make_purchase(String userID, String password){ //invalid colum name cos of view all model
        //result set is not called
        Scanner myScanner = new Scanner(System.in);
        int run1 = 0;
        ResultSet answer;
        String p ="";
        String q ="";

        do{
            System.out.println("Which of the following models are you purchasing?: ");
            viewAllModel(userID, password);
            System.out.println("Press 5 to exit");
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
                case 1: //total 
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        int t = 0;
                        p = "select model_desc from vehicle_model where model_id = \'" + c1 + "\'";
                        q = "select options from vehicle_model where model_id = \'" + c1 + "\'";
                        answer = s.executeQuery(p);
                        System.out.println("The base cost of " + answer.getString("model_desc") + " is $50,000");
                        answer = s.executeQuery(q);
                        System.out.println("With the availble options of: " + answer.getString("options"));
                        
                        do{
                            System.out.println("Would you like to purchase option(s)?"
                            + "1: Crater Descent \n"
                            + "2: Relaunch in Space \n"
                            + "3: Bundle \n"
                            + "4: None\n" );
                            while (!myScanner.hasNextInt()) {
                                String input = myScanner.next();
                                System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-4*");
                            }
                            int c2 = myScanner.nextInt();
                            switch(c2){
                                case 1:
                                System.out.println("Purchase total is: $54,000");
                                break;
                                case 2:
                                System.out.println("Purchase total is: $53,000");
                                break;
                                case 3:
                                System.out.println("Purchase total is: $55,950");
                                break;
                                case 4:
                                System.out.println("Purchase total is: $50,000");
                                break;
                            }
                        }while(t == 0);
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 2: 
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        int t = 0;
                        p = "select model_desc from vehicle_model where model_id = \'" + c1 + "\'";
                        q = "select options from vehicle_model where model_id = \'" + c1 + "\'";
                        answer = s.executeQuery(p);
                        System.out.println("The base cost of " + answer.getString("model_desc") + " is $60,000");
                        answer = s.executeQuery(q);
                        System.out.println("With the availble options of: " + answer.getString("options"));
                        
                        do{
                            System.out.println("Would you like to purchase option(s)?"
                            + "1: Ejector seats \n"
                            + "2: None\n" );
                            while (!myScanner.hasNextInt()) {
                                String input = myScanner.next();
                                System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-2*");
                            }
                            int c2 = myScanner.nextInt();
                            switch(c2){
                                case 1:
                                System.out.println("Purchase total is: $62,000");
                                break;
                                case 2:
                                System.out.println("Purchase total is: $60,000");
                                break;
                            }
                        }while(t == 0);
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 3: //total 
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        int t = 0;
                        p = "select model_desc from vehicle_model where model_id = \'" + c1 + "\'";
                        q = "select options from vehicle_model where model_id = \'" + c1 + "\'";
                        answer = s.executeQuery(p);
                        System.out.println("The base cost of " + answer.getString("model_desc") + " is $70,000");
                        answer = s.executeQuery(q);
                        System.out.println("With the availble options of: " + answer.getString("options"));
                        
                        do{
                            System.out.println("Would you like to purchase option(s)?"
                            + "1: Crater Descent \n"
                            + "2: Relaunch in Space \n"
                            + "3: Bundle \n"
                            + "4: None\n" );
                            while (!myScanner.hasNextInt()) {
                                String input = myScanner.next();
                                System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-4*");
                            }
                            int c2 = myScanner.nextInt();
                            switch(c2){
                                case 1:
                                System.out.println("Purchase total is: $74,000");
                                break;
                                case 2:
                                System.out.println("Purchase total is: $73,000");
                                break;
                                case 3:
                                System.out.println("Purchase total is: $75,950");
                                break;
                                case 4:
                                System.out.println("Purchase total is: $70,000");
                                break;
                            }
                        }while(t == 0);
                        s.close();
                        con.close();
                    }
                    catch (Exception sqle){
                        System.out.println(sqle.toString());
                    }
                break;
                case 4: //total 
                    try (
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
                        Statement s=con.createStatement();
                    ) {
                        int t = 0;
                        p = "select model_desc from vehicle_model where model_id = \'" + c1 + "\'";
                        q = "select options from vehicle_model where model_id = \'" + c1 + "\'";
                        answer = s.executeQuery(p);
                        System.out.println("The base cost of " + answer.getString("model_desc") + " is $80,000");
                        answer = s.executeQuery(q);
                        System.out.println("With the availble options of: " + answer.getString("options"));
                        
                        do{
                            System.out.println("Would you like to purchase option(s)?"
                            + "1: Ejector Seats \n"
                            + "2: Auto pilot\n"
                            + "3: Bundle \n"
                            + "4: None\n" );
                            while (!myScanner.hasNextInt()) {
                                String input = myScanner.next();
                                System.out.println("INVALID INPUT\n*PLEASE ENTER A NUMBER 1-4*");
                            }
                            int c2 = myScanner.nextInt();
                            switch(c2){
                                case 1:
                                System.out.println("Purchase total is: $82,000");
                                break;
                                case 2:
                                System.out.println("Purchase total is: $81,000");
                                break;
                                case 3:
                                System.out.println("Purchase total is: $82,550");
                                break;
                                case 4:
                                System.out.println("Purchase total is: $80,000");
                                break;
                            }
                        }while(t == 0);
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
    public void addUser(String userID, String password){
        Scanner myScanner = new Scanner(System.in);
        try (
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", userID, password);
            Statement s=con.createStatement();
        ) {
            System.out.println("input user's ID: ");
            String id = myScanner.next();
            System.out.println("input user's first name: ");
            String fn = myScanner.next();
            System.out.println("input user's last name: ");
            String ln = myScanner.next();
            System.out.println("input user's email: ");
            String email = myScanner.next();
            System.out.println("input user's address: ");
            String add = myScanner.next();
            
            System.out.println("input card number: ");
            String cn = myScanner.next();
            System.out.println("input card security code: ");
            String sc = myScanner.next();
            System.out.println("input card type: ");
            String t = myScanner.next();
            System.out.println("input card expiration ");
            String exp = myScanner.next();
            System.out.println("input card zip: ");
            String z = myScanner.next();

            s.executeUpdate("insert into card (card_number, security_code, card_type, expiration, security_code, zip) values (\'"+cn+"\', \'"+sc+"\', \'"+t+"\', \'"+exp+"\', \'"+z+"\')");
            s.executeUpdate("insert into customer (customer_id, firstname, lastname, email, address, card_number_fk) values(\'"+id+"\',\'"+fn+"\',\'"+ln+"\',\'"+email+"\',\'"+add+"\', \'"+cn+"\')");
           System.out.println("new user added");
        }
        catch (Exception sqle){
            System.out.println(sqle.toString());
        }
    }
}
