import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Repository {

    private static Repository instance;

    Properties properties;
    String user;
    String password;
    String url;

    private Repository (){
        properties = new Properties();
        loadProperties();
    }

    public static Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public void getkundlista(){
        try{
            Connection con = DriverManager.getConnection(url, user,password);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from Kund");

            while (rs.next()){
                int id = rs.getInt(1);
                String ort = rs.getString(2); //är en int, verkar fungera ändå. interessant
                String name = rs.getString(3);
                String lastname = rs.getString(4);

                //här hade man kunnat läsa in ort från ort tabellen och byt ut ort id'n mot ortens namn.



                System.out.println("ID: " + id + " name: " + name + " lastname: " + lastname + " Ort: " + ort);
            }


        }catch (SQLException e){
            System.out.println(e);
        }
    }




    public void loadProperties(){
        try {
            this.properties.load(new FileInputStream("src/user_properties.properties"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.user = this.properties.getProperty("username");
        this.password = this.properties.getProperty("password");
        this.url = this.properties.getProperty("adress");
    }

    public boolean checkForUser(String username){
        try{
            Connection con = DriverManager.getConnection(url, user,password);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select AnvändarNamn from Kund");

            while (rs.next()){
                String knownUser = rs.getString(1);
                System.out.println(knownUser);

                if(username.equals(knownUser)){
                    System.out.println("användare funnen");
                    return true;
                }
            }

            System.out.println("Användaren finns ej i systemet");

            return false;

        }catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }

    public boolean checkForPassword(String username, String givenPassword){
        try{
            Connection con = DriverManager.getConnection(url, user,password);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select AnvändarNamn, Lösenord from Kund");

            while (rs.next()){

                String knownUser = rs.getString(1);
                String knownPassword = rs.getString(2);

                if(username.equals(knownUser)){
                    if (givenPassword.equals(knownPassword)){
                        System.out.println("godkänt lösenord");
                        return true;
                    }
                }
            }

            return false;

        }catch (SQLException e){
            System.out.println(e);
            return false;
        }

    }

    public Kund getAuthenticatedUser (String username){
        try{
            Connection con = DriverManager.getConnection(url, user,password);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from Kund");

            while (rs.next()){
                int id = rs.getInt(1);
                String ort = rs.getString(2); //är en int, verkar fungera ändå. interessant
                String name = rs.getString(3);
                String lastname = rs.getString(4);
                String userName = rs.getString(5);
                int activOrder = rs.getInt(7);

                if(username.equals(userName)){
                    Kund kund = new Kund(id, ort, name, lastname, username, activOrder);
                    return kund;
                }
            }

            System.out.println("Fel vid inläsning av känd kund");
            return null;

        }catch (SQLException e){
            System.out.println(e);
            return null;
        }

    }

}
