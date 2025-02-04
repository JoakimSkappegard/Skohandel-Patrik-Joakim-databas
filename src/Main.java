import javax.swing.*;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    Kund activUser;

    public Main (){
        Repository repository = Repository.getInstance();


        activUser = inloggning();





    }

    private Kund inloggning (){

        Repository repository = Repository.getInstance();

        while(true){
            String potentialUser = JOptionPane.showInputDialog(null, "ange användarnamn");
            int passwordRetry = 0;

            if (repository.checkForUser(potentialUser)){

                while (passwordRetry < 3){

                    String presentedPassword = JOptionPane.showInputDialog(null, "ange lösenord");

                    //Lägg till möjlighet att avsluta

                    if (repository.checkForPassword(potentialUser, presentedPassword)){
                        return repository.getAuthenticatedUser(potentialUser);
                    }
                    passwordRetry++;
                    JOptionPane.showMessageDialog(null, "Lösenordet du angett är inkorrekt");

                }

            }
        }


    }








    public static void main(String[] args) {

//        Repository repository = new Repository();
//
//        repository.getkundlista();

        Main main = new Main();

    }
}