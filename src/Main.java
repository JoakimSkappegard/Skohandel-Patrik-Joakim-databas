import javax.swing.*;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    Kund activUser;
    ArrayList<Sko> stock;

    public Main (){
        Repository repository = Repository.getInstance();

        activUser = new Kund(true);

        stock = repository.getAvailableSkor();

        presenteraSkor(stock);

        laggTillSkoTillOrder(stock);

        //**************************************************



        activUser = inloggning();

        while (true){
            repository.presentCurrentOrder(activUser.activOrder);

            switch(meny()){
                case -1, 3:
                    System.exit(0);
                    break;
                case 0:
                    stock = repository.getAvailableSkor();
                    presenteraSkor(stock);

                    break;
                case 1:
                    //betala
                    break;
                case 2:
                    //logga ut
                    break;
            }
        }
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

    private void presenteraSkor(ArrayList<Sko> stock){

        System.out.println                                  ("--------------------------------SKOR I TILLGÄNGLIGT SORTIMENT-----------------------------------");
        int presentedNumber = 1;

        for (Sko sko : stock) {

            if (sko.getStock() > 0) {
                System.out.println                          ("*" + (presentedNumber) + "* " + sko.toString());
                System.out.println                          ("------------------------------------------------------------------------------------------------");
                presentedNumber++;
            }
        }
    }

    private void laggTillSkoTillOrder(ArrayList<Sko> stock){

        Repository repository = Repository.getInstance();

        int choiceint = -1;
        int chocenShoeId;
        int amountInt = 0;

        while (true){

            String choise = JOptionPane.showInputDialog(null,"Välj sko ur listan\n Du gör ditt val genom att skriva in numret som anges framför den sko du är intresserad av");
            if(choise == null){
                System.exit(0);
            }

            try{

                choiceint = Integer.parseInt(choise);

            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Felaktigt inmatat värde, Var god ange endast siffror.");
                System.out.println(e);
                continue;
            }

            chocenShoeId = findSkoId(stock,choiceint);
            if(chocenShoeId == -1){
                JOptionPane.showMessageDialog(null,"Det angivna värdet finns inte representerat i listan");
                continue;
            }

            break;

        }

        while (true){

            String amount = JOptionPane.showInputDialog(null,"Hur många av "+ findSkoName(stock,choiceint) +" önskar du köpa");
            if(amount == null){
                System.exit(0);
            }

            try{

                amountInt = Integer.parseInt(amount);

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Felaktigt inmatat värde, Var god ange endast siffror.");
                System.out.println(e);
                continue;
            }

            if (amountInt<0){

                System.out.println("var god ange ett positivt heltal");

            }else if (amountInt==0){

                System.out.println("inga skor har lagts till er order");

            }else if (amountInt>findSkoAmount(stock,chocenShoeId)){

                System.out.println("Det fins inte så många skor på lagret, var god ange ett nytt värde.");

            }else{



            }


            break;

        }

        repository.addToOrder(chocenShoeId,amountInt,activUser);



    }



    private int meny (){
        Object[] options = {"Lägg till i order", "Betala activ order", "logga ut", "avsluta"};

        int result = JOptionPane.showOptionDialog(null,"Vad önskar du göra?","", JOptionPane.YES_NO_OPTION,3, null,options,options[0]);
        return result;
    }

    private int findSkoId(ArrayList<Sko> stock,int choice){
        int presentedNumber = 1;

        if(choice == -1){
            return -1;
        }

        for (int i = 0; i < stock.size(); i++) {

            if (stock.get(i).getStock() > 0) {

                if(presentedNumber == choice) {
                    return stock.get(i).getId();
                }
                presentedNumber++;
            }
        }
        return -1;
    }

    private String findSkoName(ArrayList<Sko> stock,int choice){
        int presentedNumber = 1;

        for (int i = 0; i < stock.size(); i++) {

            if (stock.get(i).getStock() > 0) {

                if(presentedNumber == choice) {
                    return stock.get(i).getName();
                }
                presentedNumber++;
            }
        }

        return null;

    }

    private int findSkoAmount(ArrayList<Sko> stock,int skoId){

        for (int i = 0; i < stock.size(); i++) {

            if (stock.get(i).getId() == skoId) {
                return stock.get(i).getStock();
            }
        }

        return -1;

    }



    public static void main(String[] args) {

//        Repository repository = new Repository();
//
//        repository.getkundlista();

        Main main = new Main();

    }
}