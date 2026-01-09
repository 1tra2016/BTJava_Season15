import java.util.ArrayList;

public class MovieManager<T> {
    private ArrayList<T> list;

    public MovieManager() {
        list = new ArrayList<>();
    }

    public void add(T item) {
        list.add(item);
    }

    public void remove(T item) {
        list.remove(item);
    }

    public ArrayList<T> getAll() {
        return list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
