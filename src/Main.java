import javax.swing.*;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    Kund activUser;

    public Main (){
        Repository repository = Repository.getInstance();

        System.out.println(meny());

        activUser = inloggning();

        while (true){
            repository.presentCurrentOrder(activUser.activOrder);

            switch(meny()){
                case -1, 3:
                    //avsluta
                    break;
                case 0:
                    //lägg till
                    break;
                case 1:
                    //betala
                    break;
                case 2:
                    //logga ut
                    break;
            }









        }



        // pressentera lista





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


    public int meny (){
        Object[] options = {"Lägg till i order", "Betala activ order", "logga ut", "avsluta"};

        int result = JOptionPane.showOptionDialog(null,"Vad önskar du göra?","", JOptionPane.YES_NO_OPTION,3, null,options,options[0]);
        return result;
    }





    public static void main(String[] args) {

//        Repository repository = new Repository();
//
//        repository.getkundlista();

        Main main = new Main();

    }
}