import java.time.LocalDate;

public class Subject {
    private String code;
    private String name;
    private int credits;
    private LocalDate startDate;

    public Subject(String code, String name, int credits, LocalDate startDate) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.startDate = startDate;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Code: " + code + " | Name: " + name + " | Credits: " + credits + " | Start Date: " + startDate;
    }
}
