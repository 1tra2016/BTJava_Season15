import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.List;

public class SubjectManager<T> {
    private ArrayList<T> list = new ArrayList<>();

    public void add(T item) {
        list.add(item);
    }

    public boolean remove(Predicate<T> condition) {
        return list.removeIf(condition);
    }

    public void showAll() {
        if (list.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        list.forEach(System.out::println);
    }

    public Optional<T> find(Predicate<T> condition) {
        return list.stream()
                .filter(condition)
                .findFirst();
    }

    public List<T> filter(Predicate<T> condition) {
        return list.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }
}
