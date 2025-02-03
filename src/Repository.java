import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Repository {

    Properties properties;
    String user;
    String password;
    String url;

    public Repository (){
        properties = new Properties();
        loadProperties();
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

}
