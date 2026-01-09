
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    static List<Product> list = new ArrayList<Product>();
    static ShoppingCart cart = new ShoppingCart();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while(true){
            System.out.println("========MENU========");
            System.out.println("1. Xem danh sách sản phẩm của cửa hàng");
            System.out.println("2. Thêm sản phẩm vào giỏ hàng");
            System.out.println("3. Xóa sản phẩm khỏi giỏ hàng");
            System.out.println("4. Xem giỏ hàng");
            System.out.println("5. Thanh toán");
            System.out.println("0. Thoát");
            System.out.println("====================");
            System.out.println("(00. Thêm sản phẩm vào cửa hàng)");
            System.out.print("Lựa chọn của bạn: ");
            String choice = sc.nextLine();
            switch(choice){
                case "1":
                    show();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    if(removeProduct()) System.out.println("Xóa sản phẩm thành công");
                    else System.out.println("Xóa sản phẩm thất bại");
                    break;
                case "4":
                    viewCart();
                    break;
                case "5":
                    pay();
                    break;
                case "0": System.exit(0);
                case "00":
                    newProduct();
                    break;
                default:
                    System.out.println("Lựa chọn hông tồn tại!");
                    break;
            }
        }
    }
    public static void show(){
        if(list.isEmpty()){
            System.out.println("Của hàng rỗng. Đóng cửa!");
            return;
        }
        for(Product p : list){
            System.out.println(p.toString());
        }
    }
    public static void newProduct(){
        String name;
        do{
            try{
                System.out.println("Nhập tên sản phẩm");
                name = sc.nextLine();
                break;
            }
            catch(Exception e){
                System.out.println("Sai!");
                e.printStackTrace();
            }
        }while(true);

        double price;
        do{
            try{
                System.out.println("Nhập giá sản phẩm");
                price = Double.parseDouble(sc.nextLine());
                break;
            }
            catch(Exception e){
                System.out.println("Nhập số đi trời!");
            }
        }while(true);

        Product p = new Product(name, price);
        list.add(p);
    }
    public static void addProduct() {
        System.out.print("Nhập id sản phẩm muốn thêm (hoặc -1 để hủy): ");

        do {
            String id = sc.nextLine();
            if (id.equals("-1")) {
                return;
            }

            Product found = null;
            for (Product p : list) {
                if (p.getId().equals(id)) {
                    found = p;
                    break;
                }
            }

            if (found == null) {
                System.out.println("Không tìm thấy sản phẩm!");
                continue;
            }

            try {
                System.out.print("Nhập số lượng muốn mua: ");
                int quantity = Integer.parseInt(sc.nextLine());
                cart.addToCart(found, quantity);
                System.out.println("Thêm sản phẩm thành công");
                return;

            } catch (NumberFormatException e) {
                System.out.println("Số lượng không hợp lệ!");
            }

        } while (true);
    }
    public static void viewCart(){
        cart.displayCart();
    }
    public static boolean removeProduct(){
        if(cart.list.isEmpty()){
            System.out.println("Bạn chưa mua gì");
            return false;
        }
        System.out.println("Nhập id sản phẩm muốn xóa");
        try{
            String id = sc.nextLine();
            cart.removeFromCart(id);
            return true;
        }
        catch(Exception e){
            System.out.println("Sai!");
            return false;
        }
    }
    public static void pay(){
        double amount = cart.checkout();
        System.out.println(amount);
        double pay;
        do{
            try{
                System.out.println("Số tiền bạn cần trả: "+amount);
                System.out.println("Nhập số tiền bạn trả: ");
                pay = Double.parseDouble(sc.nextLine());
                if(pay<amount) {
                    amount = amount - pay;
                    continue;
                }

                if(pay>amount) System.out.println("Tôi sẽ ăn luôn "+(pay - amount)+" còn lại ");
                if(pay==amount) System.out.println("Bạn không tips à?");
                break;
            }
            catch(NumberFormatException e){
                System.out.println("Phải nhập số tiền lớn hơn số tiền cần trả!");
            }
        }while(true);
        cart.list.clear();
    }
}
