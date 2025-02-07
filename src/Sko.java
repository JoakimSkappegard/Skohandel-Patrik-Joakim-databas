public class Sko {
    private int id;
    private String name;
    private String brand;
    private String category;
    private String colour;
    private int size;
    private float price;
    private int stock;
    private int amountInOrder;

    public Sko(int id, String name, String brand, String category, String colour, int size, float price, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.colour = colour;
        this.size = size;
        this.price = price;
        this.stock = stock;
    }

    public Sko(String name,int amountInOrder, float totalPris){
        this.name = name;
        this.amountInOrder = amountInOrder;
        this.price = totalPris;
    }

    @Override
    public String toString(){

        return "Name: " + name + "| Brand: " + brand + "| Category " + category + "| Colour " + colour + "| Size " + size + "| Price " + price + "| Stock " + stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAmountInOrder() {
        return amountInOrder;
    }

    public void setAmountInOrder(int amountInOrder) {
        this.amountInOrder = amountInOrder;
    }
}
