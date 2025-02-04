import javax.swing.*;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public Main (){
        Repository repository = Repository.getInstance();

        String potentialUser = JOptionPane.showInputDialog(null, "ange användarnamn");
        if(repository.checkForUser(potentialUser)){
            String presentedPassword = JOptionPane.showInputDialog(null, "ange lösenord");

        }



    }









    public static void main(String[] args) {

//        Repository repository = new Repository();
//
//        repository.getkundlista();

        Main main = new Main();

    }
}