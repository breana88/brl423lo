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
}
