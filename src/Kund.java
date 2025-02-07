public class Kund {
    private int id;
    private String ort;
    private String name;
    private String lastname;
    private String userName;
    private String activOrder;

    public Kund(int id, String ort, String name, String lastname, String userName, String activOrder) {
        this.id = id;
        this.ort = ort;
        this.name = name;
        this.lastname = lastname;
        this.userName = userName;
        this.activOrder = activOrder;
    }

//    public Kund (boolean dummy){
//        if(dummy){
//
//            this.id = 1;
//            this.ort = "Kopparberg";
//            this.name = "Anna";
//            this.lastname = "Långström";
//            this.userName = "AnnaLångström";
//            this.activOrder = null;
//
//        }
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActivOrder() {
        return activOrder;
    }

    public void setActivOrder(String activOrder) {
        this.activOrder = activOrder;
    }


}
