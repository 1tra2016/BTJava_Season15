import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static SubjectManager<Subject> manager = new SubjectManager<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        while (true) {
            showMenu();
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        showSubjects();
                        break;
                    case 2:
                        addSubject();
                        break;
                    case 3:
                        deleteSubject();
                        break;
                    case 4:
                        searchByName();
                        break;
                    case 5:
                        filterByCredits();
                        break;
                    case 6:
                        System.out.println("Thoát chương trình.");
                        return;
                    default:
                        System.out.println("Lựa chọn không tồn tại");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số");
            }
        }
    }

    static void showMenu() {
        System.out.println("\n===== QUẢN LÝ MÔN HỌC =====");
        System.out.println("1. Hiển thị danh sách môn học");
        System.out.println("2. Thêm môn học");
        System.out.println("3. Xóa môn học theo code");
        System.out.println("4. Tìm kiếm môn học theo tên");
        System.out.println("5. Lọc môn học credits > 3");
        System.out.println("6. Thoát");
        System.out.print("Chọn: ");
    }

    static void showSubjects() {
        manager.showAll();
    }

    static void addSubject() {
        try {
            System.out.print("Code: ");
            String code = sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            int credits = inputCredits();

            LocalDate startDate = inputDate();

            manager.add(new Subject(code, name, credits, startDate));
            System.out.println("Thêm môn học thành công");
        } catch (Exception e) {
            System.out.println("Dữ liệu không hợp lệ");
        }
    }

    static void deleteSubject() {
        System.out.print("Nhập code môn học cần xóa: ");
        String code = sc.nextLine();

        boolean removed = manager.remove(
                s -> ((Subject) s).getCode().equalsIgnoreCase(code)
        );

        if (!removed) {
            System.out.println("Không tìm thấy môn học");
        } else {
            System.out.println("Xóa thành công");
        }
    }

    static void searchByName() {
        System.out.print("Nhập tên môn học: ");
        String keyword = sc.nextLine().toLowerCase();

        manager.find(
                s -> ((Subject) s).getName().toLowerCase().contains(keyword)
        ).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Không có môn học phù hợp")
        );
    }

    static void filterByCredits() {
        List<Subject> result = manager.filter(
                s -> ((Subject) s).getCredits() > 3
        );

        if (result.isEmpty()) {
            System.out.println("Không có môn học credits > 3");
        } else {
            result.forEach(System.out::println);
        }
    }

    static int inputCredits() throws Exception {
        System.out.print("Credits: ");
        int credits = Integer.parseInt(sc.nextLine());
        if (credits < 0 || credits > 10) {
            throw new Exception("Credits không hợp lệ");
        }
        return credits;
    }

    static LocalDate inputDate() {
        while (true) {
            try {
                System.out.print("Start Date (dd/MM/yyyy): ");
                return LocalDate.parse(sc.nextLine(), formatter);
            } catch (Exception e) {
                System.out.println("Sai định dạng ngày");
            }
        }
    }
}
