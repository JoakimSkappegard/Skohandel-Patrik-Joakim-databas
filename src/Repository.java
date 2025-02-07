import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Repository {

    private static Repository instance;

    private Properties properties;
    private String user;
    private String password;
    private String url;

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
        try(Connection con = DriverManager.getConnection(url, user,password);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from Kund");


        ){
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
        try(Connection con = DriverManager.getConnection(url, user,password);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select AnvändarNamn from Kund");

        ){
            while (rs.next()){
                String knownUser = rs.getString(1);


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

        try(Connection con = DriverManager.getConnection(url, user,password);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select AnvändarNamn, Lösenord from Kund");
        ){



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
        try(Connection con = DriverManager.getConnection(url, user,password);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from Kund");

        ){
            while (rs.next()){
                int id = rs.getInt(1);
                String ort = rs.getString(2); //är en int, verkar fungera ändå. interessant
                String name = rs.getString(3);
                String lastname = rs.getString(4);
                String userName = rs.getString(5);
                String activeOrder = rs.getString(7);


                if(username.equals(userName)){
                    Kund kund = new Kund(id, ort, name, lastname, username, activeOrder);
                    //System.out.println(id +ort +name +lastname +userName +activeOrder);
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

    public Order getOrder(String orderId){

        ArrayList<Sko> shoesInOrder = new ArrayList<>();

        if(orderId == null){
            return new Order();
        }else{
            try(Connection con = DriverManager.getConnection(url, user, password);

                Statement stmt = con.createStatement();

                ResultSet rs = stmt.executeQuery("select * from Beställningar");

            ) {


                int activeOrderInt = Integer.parseInt(orderId);

                while (rs.next()) {

                    int id = rs.getInt(1);
                    String pruduktNamn = rs.getString(3);
                    int antall = rs.getInt(4);
                    float pris = rs.getFloat(5);


                    if (id == activeOrderInt) {
                        Sko shoe = new Sko(pruduktNamn, antall, pris);
                        shoesInOrder.add(shoe);
                    }
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
            return new Order(shoesInOrder);
        }
    }


    public ArrayList<Sko> getAvailableSkor(){
        ArrayList<Sko> availableSkor = new ArrayList<Sko>();

        try(Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from SkoVy");

        ){

            while (rs.next()){
                int Id = rs.getInt(1);
                String name = rs.getString(2);
                String brand = rs.getString(3);
                String Category = rs.getString(4);
                String colour = rs.getString(5);
                int size = rs.getInt(6);
                float price = rs.getFloat(7);
                int stock = rs.getInt(8);

                Sko sko = new Sko(Id, name, brand, Category, colour, size, price, stock);
                availableSkor.add(sko);
            }

            return availableSkor;

        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public void addToOrder(int skoId, int antall, Kund kund){


        try(Connection con = DriverManager.getConnection(url, user, password);

            CallableStatement stmt = con.prepareCall(
                    "CALL LäggTillBeställning(?,?,?,?)"
            );

        ){

            stmt.setString(1, kund.getActivOrder());
            stmt.setInt(2, kund.getId());
            stmt.setInt(3, skoId);
            stmt.setInt(4, antall);
            stmt.execute();

        }catch (SQLException e){
            System.out.println(e);
        }


    }

    public void payOrder(Kund kund){
        try(Connection con = DriverManager.getConnection(url, user, password);

            CallableStatement stmt = con.prepareCall(
                    "CALL AvslutaBeställning(?)"
            );

        ){
            stmt.setString(1, kund.getActivOrder());
            stmt.execute();
            System.out.println("Din order är nu betald!");


        }catch (SQLException e){
            System.out.println(e);
        }

    }


    public int amountOfShoesInOrder(String orderId){

        if(orderId == null){
            return 0;
        }

        int skorIOrder = 0;

        try{

            Connection con = DriverManager.getConnection(url, user, password);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select BeställningsId, Antal  from iBeställning");

            while (rs.next()){
                if(orderId.equals(rs.getString(1))){
                    skorIOrder = skorIOrder+rs.getInt(2);
                }
            }

        }catch (SQLException e){
            System.out.println(e);
            return-1;
        }

        return skorIOrder;

    }
}
