import java.util.ArrayList;

public class Order {
    private ArrayList<Sko> shoesInOrder;

    public Order(ArrayList<Sko> shoesInOrder) {
        this.shoesInOrder = shoesInOrder;
    }

    public Order(){

    }

    public void presentCurrentOrder(Kund user){
        Repository repository = Repository.getInstance();

        if(user.getActivOrder()==null){
            System.out.println("****************************************\n\n\nDu har för nuvarande ingen aktiv order\n\n\n****************************************");
        }else{
            Order order = repository.getOrder(user.getActivOrder());
            float totalpris = 0;

            int activeOrderInt = Integer.parseInt(user.getActivOrder());

            System.out.println("****************************************\n\nDin aktiva order innehåller:");

            for (Sko sko : shoesInOrder) {
                System.out.println("produkt: " + sko.getName().toUpperCase() + "| antal: " + sko.getAmountInOrder() + "| pris: " + sko.getPrice());
                totalpris = (totalpris + sko.getPrice());
            }

            System.out.println("\nTottalsumman på din order är: "+totalpris+" Kr\n\n****************************************");

        }
    }




}
