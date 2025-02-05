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

            int passwordRetry = 0;
            String potentialUser = JOptionPane.showInputDialog(null, "ange användarnamn");

            if(potentialUser == null){

                System.exit(0);

            } else if (potentialUser.equals("")) {

                JOptionPane.showMessageDialog(null,"Fyll i ert användarnamn.");

            }else{

                if (repository.checkForUser(potentialUser)){

                    while (passwordRetry < 3){

                        String presentedPassword = JOptionPane.showInputDialog(null, "ange lösenord");

                        //Lägg till möjlighet att avsluta

                        if (presentedPassword == null){

                            System.exit(0);

                        }else if(presentedPassword.equals("")){

                            JOptionPane.showMessageDialog(null,"Inget lösenord har angetts");

                        }else{

                            if (repository.checkForPassword(potentialUser, presentedPassword)){
                                return repository.getAuthenticatedUser(potentialUser);
                            }


                            passwordRetry++;


                            if(passwordRetry <3){
                                JOptionPane.showMessageDialog(null, "Lösenordet du angett är inkorrekt");
                            }else {
                                JOptionPane.showMessageDialog(null, "Lösenordet du angett är inkorrekt\nFör många inloggnings försök har gjorts\nInloggnings processen startas om");
                            }
                        }
                    }
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