import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Movie> movies = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int autoId = 1;

    public static void main(String[] args) {
        while (true) {
            showMenu();
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        addMovie();
                        break;
                    case 2:
                        editMovie();
                        break;
                    case 3:
                        deleteMovie();
                        break;
                    case 4:
                        showMovies();
                        break;
                    case 5:
                        searchByName();
                        break;
                    case 6:
                        filterByRating();
                        break;
                    case 7:
                        System.out.println("Đã thoát chương trình.");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
            }
        }
    }

    static void showMenu() {
        System.out.println("\n===== QUẢN LÝ PHIM =====");
        System.out.println("1. Thêm phim mới");
        System.out.println("2. Sửa phim theo ID");
        System.out.println("3. Xóa phim theo ID");
        System.out.println("4. Hiển thị danh sách phim");
        System.out.println("5. Tìm kiếm phim theo tên");
        System.out.println("6. Lọc phim rating > 8.0");
        System.out.println("7. Thoát");
        System.out.print("Chọn: ");
    }

    static void addMovie() {
        System.out.print("Tên phim: ");
        String title = sc.nextLine();

        System.out.print("Đạo diễn: ");
        String director = sc.nextLine();

        System.out.print("Ngày phát hành (dd/MM/yyyy):");
        String releaseDate = sc.nextLine();

        releaseDate = validateDate(releaseDate);

        System.out.print("Điểm đánh giá (0.0 - 10.0): ");
        double rating = validateRating(sc.nextLine());

        Movie movie = new Movie(autoId++, title, director, releaseDate, rating);
        movies.add(movie);

        System.out.println("Thêm phim thành công!");
    }

    static String validateDate(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        while (true) {
            try {
                sdf.parse(input);
                return input;
            } catch (ParseException e) {
                System.out.print("Sai định dạng ngày, nhập lại (dd/MM/yyyy): ");
                input = sc.nextLine();
            }
        }
    }
    static double validateRating(String input) {
        while (true) {
            try {
                double rating = Double.parseDouble(input);
                if (rating < 0 || rating > 10) {
                    throw new Exception();
                }
                return rating;
            } catch (Exception e) {
                System.out.print("Rating không hợp lệ, nhập lại (0.0 - 10.0): ");
                input = sc.nextLine();
            }
        }
    }

    static void editMovie() {
        System.out.print("Nhập ID phim cần sửa: ");
        int id;
        while (true) { try{
            id = Integer.parseInt(sc.nextLine());
            break;
        }catch(NumberFormatException e){
            System.out.println("Phải nhập số!");
        } }

        for (Movie m : movies) {
            if (m.getId() == id) {
                System.out.println("=== Thông tin hiện tại ===");
                System.out.println(m);
                System.out.println("=== Nhập thông tin mới (Enter để giữ nguyên) ===");

                System.out.print("Tên phim mới: ");
                String title = sc.nextLine();
                if (!title.isEmpty()) {
                    m.setTitle(title);
                }

                System.out.print("Đạo diễn mới: ");
                String director = sc.nextLine();
                if (!director.isEmpty()) {
                    m.setDirector(director);
                }

                System.out.print("Ngày phát hành mới (dd/MM/yyyy): ");
                String dateInput = sc.nextLine();
                if (!dateInput.isEmpty()) {
                    m.setReleaseDate(validateDate(dateInput));
                }

                System.out.print("Điểm đánh giá mới (0.0 - 10.0): ");
                String ratingInput = sc.nextLine();
                if (!ratingInput.isEmpty()) {
                    m.setRating(validateRating(ratingInput));
                }

                System.out.println("Sửa phim thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy phim!");
    }


    static void deleteMovie() {
        int id;
        while(true){
            System.out.print("Nhập ID phim cần xóa: ");
            try{
                 id = Integer.parseInt(sc.nextLine());
                 break;
            }catch(NumberFormatException e){
                System.out.println("Phải nhập số");
            }

        }

        for (Movie m : movies) {
            if (m.getId() == id) {
                movies.remove(m);
                System.out.println("Xóa thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy phim!");
    }

    static void showMovies() {
        if (movies.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        for (Movie m : movies) {
            System.out.println(m);
        }
    }

    static void searchByName() {
        System.out.print("Nhập tên phim cần tìm: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;

        for (Movie m : movies) {
            if (m.getTitle().toLowerCase().contains(keyword)) {
                System.out.println(m);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy phim");
        }
    }

    // =======================
    // 6. Lọc rating
    static void filterByRating() {
        boolean found = false;
        for (Movie m : movies) {
            if (m.getRating() > 8.0) {
                System.out.println(m);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không có phim rating > 8.0");
        }
    }

}
