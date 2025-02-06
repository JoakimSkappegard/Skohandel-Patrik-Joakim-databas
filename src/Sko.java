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

}
