import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<CartItem> list = new ArrayList<CartItem>();

    public void addToCart(Product product, int quantity){
        boolean isfound=false;
        for(CartItem item : list){
            if(item.getProduct().equals(product)){
                item.setQuanity(item.getQuanity()+quantity);
                isfound = true;
                break;
            }
        }
        if(!isfound){
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setQuanity(quantity);
            list.add(item);
        }
    }

    public void removeFromCart(String productId) throws Exception {
        for(CartItem item : list){
            if(item.getProduct().getId().equals(productId)){
                list.remove(item);
                return;
            }
        }
        throw new Exception("Product not found");
    }

    public void displayCart(){
        if(list.isEmpty()){
            System.out.println("Bạn nghèo, chưa mua gì");
            return;
        }
        for(CartItem item : list){
            System.out.println(item.getProduct().toString() + ", số lượng: " + item.getQuanity()+", thành tiền: "+item.getProduct().getPrice()*item.getQuanity());
        }
        double amount = checkout();
        System.out.print("Tổng tiền: "+amount+"\n");
    }
    public double checkout(){
        double amount =0;
        for(CartItem item : list){
            amount= amount + item.getProduct().getPrice()*item.getQuanity();
        }
        return amount;

    }
}
