import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CartItem {
    private Product product = new Product();
    private int quanity;

    public CartItem(){}
    public CartItem(Product product, int quanity) {
        this.product = product;
        this.quanity = quanity;
    }
    public Product getProduct() {return product;}
    public void setProduct(Product product) {this.product = product;}
    public int getQuanity() {return quanity;}
    public void setQuanity(int quanity) {this.quanity = quanity;}

    public double  totalAmount() {
        return quanity*product.getPrice();
    }
}
