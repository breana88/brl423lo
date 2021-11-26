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
                p = "select firstname, lastname, email, address from customer where customer_id > 0";   

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
    public void editCustomer(){

    }
//----------------------------------------------------------------------------------------------------------------
    public String viewCard(){

        return "";
    }
    public String viewAllCard(){

        return "";
    }
    public void editCard(){

    }
//----------------------------------------------------------------------------------------------------------------
    public String viewVehicle(){

        return "";
    }
    public String viewAllVehicle(){

        return "";
    }
    public void editVehicle(){

    }
//----------------------------------------------------------------------------------------------------------------
    public String viewModel(){

        return "";
    }
    public String viewAllModel(){

        return "";
    }
    public void editModel(){

    }
//----------------------------------------------------------------------------------------------------------------
    public String viewAllServiceLocations(){

        return "";
    }
    public void editServiceLocations(){

    }
//----------------------------------------------------------------------------------------------------------------
    public String viewAllShowroom(){

        return "";
    }
    public void editShowroom(){

    }
//----------------------------------------------------------------------------------------------------------------
    public String viewPrice(){

        return "";
    }
    public String viewAllPrice(){

        return "";
    }
    public void editPrice(){

    }
//----------------------------------------------------------------------------------------------------------------
    public String viewRepairs(){

        return "";
    }
    public String viewAllRepairs(){

        return "";
    }
    public void editRepairs(){

    }
}
