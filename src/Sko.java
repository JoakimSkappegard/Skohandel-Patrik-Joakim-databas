public class Sko {
    int id;
    String name;
    String brand;
    String category;
    String colour;
    int size;
    float price;
    int stock;

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
}
