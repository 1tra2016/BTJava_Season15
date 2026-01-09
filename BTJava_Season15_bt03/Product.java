

import java.util.Scanner;
import java.util.UUID;
import java.util.Random;

public class Product {

    static Random random = new Random();

    private String id;
    private String name;
    private double price;

    public Product() {}

    public Product(String name, double price) {
        setId();
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public void setId() {
        int randomId = random.nextInt(1_000); // 0 → 999999
        this.id = String.format("%03d", randomId);
    }

    public  String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Id: "+ id + ", tên: " + name + ", đơn giá: " +price;
    }
}
